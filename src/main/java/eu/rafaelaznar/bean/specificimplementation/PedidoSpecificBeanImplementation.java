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
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import java.util.Date;

@MetaObjectBeanInterface(
        TableName = "pedido",
        SingularDescription = "Pedido",
        PluralDescription = "Pedidos",
        Icon = "fa fa-file-text",
        SqlSelect = "SELECT * FROM pedido WHERE 1=1 ",
        SqlSelectCount = "SELECT COUNT(*) FROM pedido WHERE 1=1 ",
        Type = EnumHelper.SourceType.Table
)
public class PedidoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Fecha",
            LongName = "Fecha del pedido",
            Description = "Fecha de pedido del usuario",
            Type = EnumHelper.FieldType.Date,
            IsForeignKeyDescriptor = true,
            IsRequired = true
    )
    private Date fecha;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "%IVA",
            LongName = "Porcentaje IVA",
            Description = "Impuesto del valor añadido IVA",
            Type = EnumHelper.FieldType.Integer,
            IsRequired = true
    )
    private Integer iva;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "¿IVA?",
            LongName = "¿Lleva IVA?",
            Description = "¿Lleva IVA?",
            Type = EnumHelper.FieldType.Boolean,
            IsRequired = true
    )
    private Integer tiene_iva;

    @Expose(serialize = false)
    private Integer id_usuario = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Cliente",
            LongName = "Cliente que realiza el pedido",
            Description = "Cliente que realiza el pedido",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "usuario",
            IsVisible = true,
            Wide = 5
    )
    private MetaBeanHelper obj_usuario = null;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Lineas",
            LongName = "Líneas del pedido",
            Description = "Líneas del pedido",
            Type = EnumHelper.FieldType.Link,
            References = "linea_pedido"
    )
    private Integer link_linea_pedido = null;

    public PedidoSpecificBeanImplementation() {

    }

    public PedidoSpecificBeanImplementation(Integer id) {
        super(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public MetaBeanHelper getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(MetaBeanHelper obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public int getTiene_iva() {
        return tiene_iva;
    }

    public void setTiene_iva(int tiene_iva) {
        this.tiene_iva = tiene_iva;
    }

    public Integer getLink_linea_pedido() {
        return link_linea_pedido;
    }

    public void setLink_linea_pedido(Integer link_linea_pedido) {
        this.link_linea_pedido = link_linea_pedido;
    }

}
