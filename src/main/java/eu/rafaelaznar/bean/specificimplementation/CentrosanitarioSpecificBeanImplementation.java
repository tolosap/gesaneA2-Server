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
        TableName = "centrosanitario",
        SingularDescription = "Centro sanitario",
        PluralDescription = "Centros sanitarios",
        Icon = "fa fa-user-o",
        SqlSelect = "SELECT * FROM centrosanitario WHERE 1=1 ",
        SqlSelectCount = "SELECT COUNT(*) FROM centrosanitario WHERE 1=1 ",
        Type = EnumHelper.SourceType.Table
)
public class CentrosanitarioSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cto.Sanit.",
            LongName = "Centro Sanitario",
            Description = "Centro Sanitario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

    public CentrosanitarioSpecificBeanImplementation() {
    }

    CentrosanitarioSpecificBeanImplementation(Integer id_centrosanitario) {
        id = id_centrosanitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
