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
import static eu.rafaelaznar.helper.EnumHelper.FieldType.Date;
import eu.rafaelaznar.helper.constant.RegexConstants;
import java.util.Date;


@MetaObjectBeanInterface(
        TableName = "factura",
        SingularDescription = "Factura",
        PluralDescription = "Factura",
        Icon = "fa fa-file-archive-o",
        Type = EnumHelper.SourceType.Table
)
public class FacturaSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Descripción",
            LongName = "Descripción",
            Description = "Descripción de la factura",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    )
    private String descripcion;
    
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Factura",
//            LongName = "Factura del episodio",
//            Description = "Factura del episodio",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodio"
//    )
//    private Integer link_episodio = null;
    
        @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Fecha",
            LongName = "Fecha",
            Description = "Fecha de la factura",
            Type = EnumHelper.FieldType.Date,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    
    )
private Date fecha; 

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
