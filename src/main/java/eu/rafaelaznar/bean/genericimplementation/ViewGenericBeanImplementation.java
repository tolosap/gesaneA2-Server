/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * trolleyes-server3: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server3
 * 
 * trolleyes-server3 is distributed under the MIT License (MIT)
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

import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import java.sql.Connection;
import java.sql.ResultSet;
import eu.rafaelaznar.bean.publicinterface.GenericBeanInterface;
import eu.rafaelaznar.dao.publicinterface.TableDaoInterface;
import eu.rafaelaznar.factory.DaoFactory;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class ViewGenericBeanImplementation implements GenericBeanInterface {

    public ViewGenericBeanImplementation() {

    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    @Override
    public GenericBeanInterface fill(ResultSet oResultSet, Connection oConnection, UsuarioSpecificBeanImplementation oPuserBean_security, Integer expand) throws Exception {
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
            if (x.getName().startsWith("obj_")) {
                if (expand > 0) {
                    String ob = x.getName().substring(x.getName().indexOf("_") + 1);
                    TableDaoInterface oObDao = (TableDaoInterface) DaoFactory.getDao(ob, oConnection, oPuserBean_security, null);
                    TableGenericBeanImplementation oObBean = (TableGenericBeanImplementation) oObDao.get(oResultSet.getInt("id_" + ob), expand - 1);
                    x.set(this, oObBean);
                }
            } else {
                if (x.getName().startsWith("id_")) {
                    x.set(this, oResultSet.getInt(x.getName()));
                } else {
                    if (x.getType() == String.class) {
                        x.set(this, oResultSet.getString(x.getName()));
                    }
                    if (x.getType() == Date.class) {
                        x.set(this, oResultSet.getDate(x.getName()));
                    }
                    if (x.getType() == Double.class) {
                        x.set(this, oResultSet.getDouble(x.getName()));
                    }
                    if (x.getType() == Integer.class || x.getType() == int.class) {
                        x.set(this, oResultSet.getInt(x.getName()));
                    }
                }
            }
            x.setAccessible(false);
        }
        return this;
    }
}
