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
package eu.rafaelaznar.bean.meta.helper;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.helper.EnumHelper;

public class MetaObjectGenericBeanHelper {

    private String ClassName = "";
    @Expose
    private String Icon = "";
    @Expose
    private String SingularDescription = "";
    @Expose
    private String PluralDescription = "";
    @Expose
    private EnumHelper.SourceType Type = EnumHelper.SourceType.Table;
    @Expose
    private String TableName = "";

    private String SqlSelect = "";
    private String SqlSelectCount = "";

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String CName) {
        this.ClassName = CName;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

   
    public String getSingularDescription() {
        return SingularDescription;
    }

    public void setSingularDescription(String SingularDescription) {
        this.SingularDescription = SingularDescription;
    }

    public String getPluralDescription() {
        return PluralDescription;
    }

    public void setPluralDescription(String PluralDescription) {
        this.PluralDescription = PluralDescription;
    }

    public EnumHelper.SourceType getType() {
        return Type;
    }

    public void setType(EnumHelper.SourceType Type) {
        this.Type = Type;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getSqlSelect() {
        return SqlSelect;
    }

    public void setSqlSelect(String SqlSelect) {
        this.SqlSelect = SqlSelect;
    }

    public String getSqlSelectCount() {
        return SqlSelectCount;
    }

    public void setSqlSelectCount(String SqlSelectCount) {
        this.SqlSelectCount = SqlSelectCount;
    }

}
