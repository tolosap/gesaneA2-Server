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

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.bean.publicinterface.GenericBeanInterface;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import java.lang.reflect.Field;
import java.util.Date;

public abstract class TableGenericBeanImplementation extends ViewGenericBeanImplementation implements GenericBeanInterface {

    @Expose
    @MetaPropertyBeanInterface(
            IsId = true,
            Name = "id",
            ShortName = "ID",
            LongName = "Identificador",
            Description = "Número Identificador de registro",
            IsVisible = true,
            Type = EnumHelper.FieldType.Id
    )
    protected Integer id;

    public TableGenericBeanImplementation() {

    }

    public TableGenericBeanImplementation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getColumns() throws Exception {
        String strColumns = "";
        try {
            TableGenericBeanImplementation oBean = (TableGenericBeanImplementation) Class.forName(this.getClass().getName()).newInstance();
            Field[] oFields = oBean.getClass().getDeclaredFields();
            for (Field x : oFields) {
                if (!x.getName().startsWith("obj_")) {
                    strColumns += x.getName() + ",";
                }
            }
            strColumns = strColumns.substring(0, strColumns.length() - 1);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return strColumns;
    }

    @Override
    public String getValues() throws Exception {
        String strColumns = "";
        try {
            TableGenericBeanImplementation oBean = (TableGenericBeanImplementation) Class.forName(this.getClass().getName()).newInstance();
            Field[] oFields = oBean.getClass().getDeclaredFields();
            for (Field x : oFields) {
                if (!x.getName().startsWith("obj_")) {
                    if (x.getName().equals("password")) {
                        strColumns += EncodingHelper.quotate("da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04") + ", ";
                    } else {
                        x.setAccessible(true);
                        if (x.getType() == String.class) {
                            strColumns += EncodingHelper.quotate((String) x.get(this)) + ",";
                        } else {
                            if (x.getType() == Date.class) {
                                strColumns += EncodingHelper.stringifyAndQuotate((Date) x.get(this)) + ",";
                            } else {
                                if (x.getType() == Integer.class) {
                                    strColumns += (Integer) x.get(this) + ",";
                                } else {
                                    if (x.getType() == Double.class) {
                                        strColumns += (Double) x.get(this) + ",";
                                    } else {
                                        if (x.getType() == Boolean.class) {
                                            strColumns += (int) x.get(this) + ",";
                                        }
                                    }
                                }
                            }
                        }
                        x.setAccessible(false);
                    }
                }
            }
            strColumns = strColumns.substring(0, strColumns.length() - 1);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return strColumns;
    }

    @Override
    public String toPairs() throws Exception {
        String strColumns = "";
        try {
            TableGenericBeanImplementation oBean = (TableGenericBeanImplementation) Class.forName(this.getClass().getName()).newInstance();
            Field[] oFields = oBean.getClass().getDeclaredFields();
            for (Field x : oFields) {
                if (!x.getName().startsWith("obj_")) {
                    if (!x.getName().equals("password")) {
                        strColumns += x.getName() + "=";
                        x.setAccessible(true);
                        if (x.getType() == String.class) {
                            strColumns += EncodingHelper.quotate((String) x.get(this)) + ",";
                        } else {
                            if (x.getType() == Date.class) {
                                strColumns += EncodingHelper.stringifyAndQuotate((Date) x.get(this)) + ",";
                            } else {
                                if (x.getType() == Integer.class) {
                                    strColumns += (Integer) x.get(this) + ",";
                                } else {
                                    if (x.getType() == Double.class) {
                                        strColumns += (Double) x.get(this) + ",";
                                    }
                                }
                            }
                        }
                        x.setAccessible(false);
                    }
                }
            }
            strColumns = strColumns.substring(0, strColumns.length() - 1);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return strColumns;
    }

}
