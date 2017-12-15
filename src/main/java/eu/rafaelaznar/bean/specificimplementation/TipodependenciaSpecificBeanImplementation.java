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
 * @author a0y3770325h
 */

@MetaObjectBeanInterface(
        TableName = "Tipodependencia",
        SingularDescription = "Tipo de dependencia",
        PluralDescription = "Tipo de dependencias",
        Icon = "fa fa-yelp",
        Type = EnumHelper.SourceType.Table
)
        
public class TipodependenciaSpecificBeanImplementation extends TableGenericBeanImplementation {
    
    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tipodep.desc.",
            LongName = "Descripcion de tipo dependencia",
            Description = "Descripcion de la tabla tipo dependencia",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            // RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";
    
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Tipo dep.",
//            LongName = "Tipo de Dependencia",
//            Description = "Tipo de DEPENDENCIA",
//            Type = EnumHelper.FieldType.Link,
//            References = "dependencia"
//    )
//    private Integer link_dependencia = null;

    public TipodependenciaSpecificBeanImplementation() {
    }

    TipodependenciaSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
    

