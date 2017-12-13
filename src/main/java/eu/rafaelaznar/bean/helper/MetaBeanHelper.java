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
package eu.rafaelaznar.bean.helper;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.meta.helper.MetaObjectGenericBeanHelper;
import eu.rafaelaznar.bean.meta.helper.MetaPropertyGenericBeanHelper;
import java.util.ArrayList;

public class MetaBeanHelper {

    @Expose
    private ArrayList<MetaPropertyGenericBeanHelper> metaProperties;
    @Expose
    private MetaObjectGenericBeanHelper metaObject;
    @Expose
    private Object data;

    public MetaBeanHelper() {
    }

    public MetaBeanHelper(MetaObjectGenericBeanHelper oMetaObject, ArrayList<MetaPropertyGenericBeanHelper> alMetaProperties, Object oBean) {

        this.metaProperties = alMetaProperties;
        this.metaObject = oMetaObject;
        this.data = oBean;
    }

    public ArrayList<MetaPropertyGenericBeanHelper> getMetaProperties() {
        return metaProperties;
    }

    public void setMetaProperties(ArrayList<MetaPropertyGenericBeanHelper> metaProperties) {
        this.metaProperties = metaProperties;
    }

    public MetaObjectGenericBeanHelper getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(MetaObjectGenericBeanHelper metaObject) {
        this.metaObject = metaObject;
    }

    public Object getBean() {
        return data;
    }

    public void setBean(TableGenericBeanImplementation bean) {
        this.data = bean;
    }

}
