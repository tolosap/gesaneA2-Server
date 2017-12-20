/*
 * Copyright (c) 2017-2018 
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & DAW students
 * 
 * GESANE: Free Open Source Health Management System
 *
 * Sources at:
 *                            https://github.com/rafaelaznar/gesane-server
 *                            https://github.com/rafaelaznar/gesane-client
 *                            https://github.com/rafaelaznar/gesane-database
 *
 * GESANE is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.rafaelaznar.bean.genericimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import eu.rafaelaznar.bean.publicinterface.GenericBeanInterface;
import eu.rafaelaznar.dao.publicinterface.TableDaoInterface;
import eu.rafaelaznar.factory.DaoFactory;
import eu.rafaelaznar.helper.EnumHelper.FieldType;
import eu.rafaelaznar.helper.Log4jHelper;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

public abstract class ViewGenericBeanImplementation implements GenericBeanInterface {

    public ViewGenericBeanImplementation() {

    }

    private String getOwnNameFromObjectMetaData() {
        String strOwnTable = "";
        Annotation[] classAnnotations = this.getClass().getAnnotationsByType(MetaObjectBeanInterface.class);
        for (Integer i = 0; i < classAnnotations.length; i++) {
            if (classAnnotations[i].annotationType().equals(MetaObjectBeanInterface.class)) {
                MetaObjectBeanInterface fieldAnnotation = (MetaObjectBeanInterface) classAnnotations[i];
                strOwnTable = fieldAnnotation.TableName();
            }
        }
        return strOwnTable;
    }

    protected FieldType getTypeFromPropertyMetaData(Field field) {
        FieldType f = null;
        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
        for (Integer i = 0; i < fieldAnnotations.length; i++) {
            if (fieldAnnotations[i].annotationType().equals(MetaPropertyBeanInterface.class)) {
                MetaPropertyBeanInterface fieldAnnotation = (MetaPropertyBeanInterface) fieldAnnotations[i];
                f = fieldAnnotation.Type();
            }
        }
        return f;
    }

    private String getReferencesFromPropertyMetaData(Field field) {
        String f = null;
        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
        for (Integer i = 0; i < fieldAnnotations.length; i++) {
            if (fieldAnnotations[i].annotationType().equals(MetaPropertyBeanInterface.class)) {
                MetaPropertyBeanInterface fieldAnnotation = (MetaPropertyBeanInterface) fieldAnnotations[i];
                f = fieldAnnotation.References();
            }
        }
        return f;
    }

    @Override
    public GenericBeanInterface fill(ResultSet oResultSet, Connection oConnection, MetaBeanHelper oPuserBean_security, Integer expand) throws Exception {
        try {
            ViewGenericBeanImplementation oBean = (ViewGenericBeanImplementation) Class.forName(this.getClass().getName()).newInstance();
            if (this.getClass().getSuperclass() == TableGenericBeanImplementation.class) {
                Field x = this.getClass().getSuperclass().getDeclaredField("id");
                x.setAccessible(true);
                x.set(this, oResultSet.getInt("id"));
                x.setAccessible(false);
            }
            Field[] oFields = oBean.getClass().getDeclaredFields();
            for (Field x : oFields) {
                x.setAccessible(true);
                if (getTypeFromPropertyMetaData(x) != null) {
                    if (getTypeFromPropertyMetaData(x) != FieldType.Calculated) {
                        if (getTypeFromPropertyMetaData(x) == FieldType.ForeignObject) {
                            if (expand > 0) {
                                String ob = getReferencesFromPropertyMetaData(x);
                                TableDaoInterface oObDao = (TableDaoInterface) DaoFactory.getDao(ob, oConnection, oPuserBean_security, null);
                                MetaBeanHelper oMetaBeanHelper = (MetaBeanHelper) oObDao.get(oResultSet.getInt("id_" + ob), expand - 1);
                                x.set(this, oMetaBeanHelper);
                            }
                        } else {
                            if (getTypeFromPropertyMetaData(x) == FieldType.Link) {
                                String ob = getReferencesFromPropertyMetaData(x);
                                TableDaoInterface oObDao = (TableDaoInterface) DaoFactory.getDao(ob, oConnection, oPuserBean_security, " and id_" + getOwnNameFromObjectMetaData() + "=" + oResultSet.getInt("id"));
                                if (oObDao != null) { //en el proceso de login puede ser nulo!!
                                    x.set(this, oObDao.getCount(null).intValue());
                                } else {
                                    x.set(this, 0);
                                }
                            } else {
                                if (getTypeFromPropertyMetaData(x) == FieldType.ForeignId) {
                                    x.set(this, oResultSet.getInt(x.getName()));
                                } else {
                                    if (x.getType() == String.class) {
                                        x.set(this, oResultSet.getString(x.getName()));
                                    } else {
                                        if (x.getType() == Date.class) {
                                            x.set(this, oResultSet.getDate(x.getName()));
                                        } else {
                                            if (x.getType() == Double.class || x.getType() == double.class) {
                                                x.set(this, oResultSet.getDouble(x.getName()));
                                            } else {
                                                if (x.getType() == Integer.class || x.getType() == int.class) {
                                                    x.set(this, oResultSet.getInt(x.getName()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                x.setAccessible(false);
            }

            this.ComputeCalculatedFields();

        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return this;
    }

    @Override
    public void ComputeCalculatedFields() {

    }
}
