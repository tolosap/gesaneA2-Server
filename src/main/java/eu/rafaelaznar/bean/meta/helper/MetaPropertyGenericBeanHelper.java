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
package eu.rafaelaznar.bean.meta.helper;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.helper.EnumHelper;

public class MetaPropertyGenericBeanHelper {

    @Expose
    private String Name = "";
    @Expose
    private String ShortName = "";
    @Expose
    private String LongName = "";
    @Expose
    private String Description = "";
    @Expose
    private boolean IsId = false;
    @Expose
    private boolean IsIdForeignKey = false;
    @Expose
    private boolean IsObjForeignKey = false;
    @Expose
    private String References = "";
    @Expose
    private boolean IsForeignKeyDescriptor = false;
    @Expose
    private EnumHelper.FieldType Type = EnumHelper.FieldType.String;
    @Expose
    private boolean IsRequired = false;
    @Expose
    private String RegexPattern = "";
    @Expose
    private String RegexHelp = "";
    @Expose
    private String DefaultValue = "";
    @Expose
    private boolean IsVisible = true;
    @Expose
    private Integer Width = 2;
    @Expose
    private Integer MaxLength = 255;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    public String getLongName() {
        return LongName;
    }

    public void setLongName(String LongName) {
        this.LongName = LongName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public boolean isIsId() {
        return IsId;
    }

    public void setIsId(boolean IsId) {
        this.IsId = IsId;
    }

    public boolean isIsIdForeignKey() {
        return IsIdForeignKey;
    }

    public void setIsIdForeignKey(boolean IsIdForeignKey) {
        this.IsIdForeignKey = IsIdForeignKey;
    }

    public boolean isIsObjForeignKey() {
        return IsObjForeignKey;
    }

    public void setIsObjForeignKey(boolean IsObjForeignKey) {
        this.IsObjForeignKey = IsObjForeignKey;
    }

    public String getReferences() {
        return References;
    }

    public void setReferences(String References) {
        this.References = References;
    }

    public boolean isIsForeignKeyDescriptor() {
        return IsForeignKeyDescriptor;
    }

    public void setIsForeignKeyDescriptor(boolean IsForeignKeyDescriptor) {
        this.IsForeignKeyDescriptor = IsForeignKeyDescriptor;
    }

    public EnumHelper.FieldType getType() {
        return Type;
    }

    public void setType(EnumHelper.FieldType Type) {
        this.Type = Type;
    }

    public boolean isIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(boolean IsRequired) {
        this.IsRequired = IsRequired;
    }

    public String getRegexPattern() {
        return RegexPattern;
    }

    public void setRegexPattern(String RegexPattern) {
        this.RegexPattern = RegexPattern;
    }

    public String getRegexHelp() {
        return RegexHelp;
    }

    public void setRegexHelp(String RegexHelp) {
        this.RegexHelp = RegexHelp;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String DefaultValue) {
        this.DefaultValue = DefaultValue;
    }

    public boolean isIsVisible() {
        return IsVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.IsVisible = isVisible;
    }

    public Integer getWidth() {
        return Width;
    }

    public void setWidth(Integer Width) {
        this.Width = Width;
    }

    public Integer getMaxLength() {
        return MaxLength;
    }

    public void setMaxLength(Integer MaxLength) {
        this.MaxLength = MaxLength;
    }

}
