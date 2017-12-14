/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.constant.RegexConstants;

/**
 *
 * @author a023321991z
 */
@MetaObjectBeanInterface(
        TableName = "sexo",
        SingularDescription = "Sexo",
        PluralDescription = "Sexo",
        Icon = "fa fa-venus-mars",
        Type = EnumHelper.SourceType.Table
)
public class SexoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Sexo",
            LongName = "Sexo",
            Description = "Sexo del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    )
    private String descripcion;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Sexo",
            LongName = "Sexo del paciente",
            Description = "Sexo del paciente",
            Type = EnumHelper.FieldType.Link,
            References = "paciente"
    )
    private Integer link_paciente = null;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
