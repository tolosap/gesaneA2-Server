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
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.constant.RegexConstants;

@MetaObjectBeanInterface(
        TableName = "producto",
        Description = "Productos del sistema",
        Icon = "fa fa-gift",
        SqlSelect = "SELECT * FROM producto WHERE 1=1 ",
        SqlSelectCount = "SELECT COUNT(*) FROM producto WHERE 1=1 ",
        Type = EnumHelper.SourceType.Table
)
public class ProductoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cod.",
            LongName = "Código",
            Description = "Código del producto",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = "[^A-Z0-9]",
            RegexHelp = "letras mayúsculas y números",
            IsForeignKeyDescriptor = true,
            MaxLength = 10
    )
    private String codigo;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Desc.",
            LongName = "Descripción",
            Description = "Descripción del producto",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            Wide = 3,
            MaxLength = 100
    )
    private String descripcion;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Exist.",
            LongName = "Existencias",
            Description = "Existencias del producto en almacén",
            Type = EnumHelper.FieldType.Integer,
            IsRequired = true,
            Wide = 3,
            MaxLength = 5
    )
    private int existencias;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "PVP",
            LongName = "Precio",
            Description = "Precio del producto",
            Type = EnumHelper.FieldType.Decimal,
            IsRequired = true,
            Wide = 3,
            MaxLength = 6
    )
    private double precio;

    public ProductoSpecificBeanImplementation() {
    }

    public ProductoSpecificBeanImplementation(Integer id) {
        super(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

//    @Override
//    public String getColumns() {
//        String strColumns = "";
//        strColumns += "codigo,";
//        strColumns += "descripcion,";
//        strColumns += "existencias,";
//        strColumns += "precio";
//        return strColumns;
//    }
//
//    @Override
//    public String getValues() {
//        String strColumns = "";
//        strColumns += id + ",";        
//        strColumns += EncodingHelper.quotate(codigo) + ",";
//        strColumns += EncodingHelper.quotate(descripcion) + ",";
//        strColumns += existencias + ",";
//        strColumns += precio;
//        return strColumns;
//    }
//
//    @Override
//    public String toPairs() {
//        String strPairs = "";
//        strPairs += "codigo=" + EncodingHelper.quotate(codigo) + ",";
//        strPairs += "descripcion=" + EncodingHelper.quotate(descripcion) + ",";
//        strPairs += "existencias=" + existencias + ",";
//        strPairs += "precio=" + precio;
//        return strPairs;
//    }
//
//    @Override
//    public GenericBeanInterface fill(ResultSet oResultSet, Connection oConnection, UsuarioSpecificBeanImplementation oPuserBean_security, Integer expand) throws SQLException, Exception {
//        this.setCodigo(oResultSet.getString("codigo"));
//        this.setDescripcion(oResultSet.getString("descripcion"));
//        this.setExistencias(oResultSet.getInt("existencias"));
//        this.setPrecio(oResultSet.getDouble("precio"));
//        return this;
//    }
}
