/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.constant.RegexConstants;

/**
 *
 * @author a022583952e
 */
@MetaObjectBeanInterface(
        TableName = "circunstanciasalta",
        SingularDescription = "Circunstancia del alta",
        PluralDescription = "Circunstancias del alta",
        Icon = "fa fa-blind",
        Type = EnumHelper.SourceType.Table
)

public class CircunstanciasaltaSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "C.Alta",
            LongName = "Circunstancias del alta",
            Description = "Circunstancias de alta del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es circunstancia de alta de los episodios",
//            LongName = "Es circunstancia de alta de los episodios",
//            Description = "Es circunstancia de alta de los episodios",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodio"
//    )
//    private Integer link_episodio = null;

    public CircunstanciasaltaSpecificBeanImplementation() {
    }

    public CircunstanciasaltaSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

}
