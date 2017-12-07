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
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.constant.RegexConstants;
import java.util.Date;

@MetaObjectBeanInterface(
        TableName = "usuario",
        SingularDescription = "Usuario",
        PluralDescription = "Usuarios",
        Icon = "fa fa-user",
        SqlSelect = "SELECT * FROM usuario WHERE 1=1 ",
        SqlSelectCount = "SELECT COUNT(*) FROM usuario WHERE 1=1 ",
        Type = EnumHelper.SourceType.Table
)
public class UsuarioSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "DNI",
            LongName = "DNI",
            Description = "Documento nacional de identidad",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.dni,
            RegexHelp = RegexConstants.dni_Help,
            MaxLength = 9
    )
    private String dni;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Nombre",
            LongName = "Nombre",
            Description = "Nombre del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    )
    private String nombre;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "1er. Ap.",
            LongName = "Primer Apellido",
            Description = "Primer Apellido del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    )
    private String primer_apellido;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "2º Ap.",
            LongName = "Segundo Apellido",
            Description = "Segundo Apellido del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = true,
            Wide = 3,
            MaxLength = 100
    )
    private String segundo_apellido;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "login",
            LongName = "Login",
            Description = "Login para entrar en el sistema",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.nameWithEndingNumbers,
            RegexHelp = RegexConstants.nameWithEndingNumbers_Help,
            MaxLength = 15
    )
    private String login;

    @Expose(serialize = false)
    private String password;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "email",
            LongName = "Correo electrónico",
            Description = "Correo electrónico del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.email,
            RegexHelp = RegexConstants.email_Help,
            MaxLength = 50
    )
    private String email;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.nacimiento",
            LongName = "Fecha de nacimiento",
            Description = "Fecha de nacimiento del usuario",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true,
            IsVisible = false
    )
    private Date fecha_nacimiento;

    @Expose(serialize = false)
    private Integer id_tipousuario = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo usuario",
            Description = "Tipo de usuario",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "tipousuario",
            Wide = 4
    )
    private MetaBeanHelper obj_tipousuario = null;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Pedidos",
            LongName = "Pedidos",
            Description = "Pedidos del usuario",
            Type = EnumHelper.FieldType.Link,
            References = "pedido"
    )
    private Integer link_pedido = null;

    public UsuarioSpecificBeanImplementation() {

    }

    public UsuarioSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(Integer id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public MetaBeanHelper getObj_tipousuario() {
        return obj_tipousuario;
    }

    public void setObj_tipousuario(MetaBeanHelper obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }

}
