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

@MetaObjectBeanInterface(
        TableName = "tiposervicio",
        SingularDescription = "Tipo de Servicio",
        PluralDescription = "Tipos de Servicio",
        Icon = "fa fa-stack-exchange",
        Type = EnumHelper.SourceType.Table
)
public class TiposervicioSpecificBeanImplementation extends TableGenericBeanImplementation{
    
    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo de Servicio",
            Description = "Servicio a suministrar",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";
    
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Tipo de servicio en función del servicio",
//            LongName = "Tipo de servicio en función del servicio",
//            Description = "Tipo de servicio en función del servicio",
//            Type = EnumHelper.FieldType.Link,
//            References = "servicio"
//    )
//    private Integer link_servicio = null;

    public TiposervicioSpecificBeanImplementation() {
    }

    public TiposervicioSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
