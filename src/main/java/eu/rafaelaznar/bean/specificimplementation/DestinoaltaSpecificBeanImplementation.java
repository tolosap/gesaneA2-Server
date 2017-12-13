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
        TableName = "destinoalta",
        SingularDescription = "Destino de Alta",
        PluralDescription = "Destinos de Alta",
         Icon = "fa fa-ellipsis-v",
        Type = EnumHelper.SourceType.Table
)
public class DestinoaltaSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Alta",
            LongName = "Destino de Alta",
            Description = "Destino al que se va a dar de alta",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

    public DestinoaltaSpecificBeanImplementation() {
    }

    public DestinoaltaSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }
}
