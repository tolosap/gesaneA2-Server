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
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import java.util.Date;

@MetaObjectBeanInterface(
        Name = "UsuarioSpecificBeanImplementation",        
        TableName = "usuario",
        Description = "Usuarios del sistema",
        Icon = "fa-user",
        SqlSelect = "SELECT * FROM usuario WHERE 1=1 ",
        SqlSelectCount = "SELECT CONT(*) FROM usuario WHERE 1=1 ",
        Type = EnumHelper.SourceType.Table
)
public class UsuarioSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            IsId = false,
            Name = "dni",
            ShortName = "DNI",
            LongName = "DNI",
            Description = "Documento nacional de identidad",
            Type = EnumHelper.FieldType.String,
            IsRequired = true            
    )
    private String dni;

    @Expose
    @MetaPropertyBeanInterface(
            IsId = false,
            Name = "nombre",
            ShortName = "Nombre",
            LongName = "Nombre",
            Description = "Nombre del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true            
    )
    private String nombre;
    
    @Expose    
    @MetaPropertyBeanInterface(
            IsId = false,
            Name = "primer_apellido",
            ShortName = "1er. Ap.",
            LongName = "Primer Apellido",
            Description = "Primer Apellido del usuario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true            
    )        
    private String primer_apellido;
    
    @Expose
    private String segundo_apellido;
    @Expose
    private String login;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String email;
    @Expose
    private Date fecha_nacimiento;

    @Expose(serialize = false)
    private Integer id_tipousuario = 0;
    @Expose(deserialize = false)
    private TipousuarioSpecificBeanImplementation obj_tipousuario = null;

    public UsuarioSpecificBeanImplementation() {

    }

    public UsuarioSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TipousuarioSpecificBeanImplementation getObj_tipousuario() {
        return obj_tipousuario;
    }

    public void setObj_tipousuario(TipousuarioSpecificBeanImplementation obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }

//    @Override
//    public GenericBeanInterface fill(ResultSet oResultSet, Connection oConnection,
//             UsuarioSpecificBeanImplementation oPuserBean_security, Integer expand) throws SQLException, Exception {
//        this.setId(oResultSet.getInt("id"));
//        this.setDni(oResultSet.getString("dni"));
//        this.setNombre(oResultSet.getString("nombre"));
//        this.setPrimer_apellido(oResultSet.getString("primer_apellido"));
//        this.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
//        this.setLogin(oResultSet.getString("login"));
//        this.setPassword(oResultSet.getString("password"));
//        this.setEmail(oResultSet.getString("email"));
//        this.setFecha_nacimiento(oResultSet.getDate("fecha_nacimiento"));
//        this.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
//        if (expand > 0) {
//            TipousuarioSpecificBeanImplementation oTipousuarioBean = new TipousuarioSpecificBeanImplementation();
//            TipousuarioSpecificDaoImplementation oTipousuarioDao = new TipousuarioSpecificDaoImplementation(oConnection, oPuserBean_security, null);
//            oTipousuarioBean = (TipousuarioSpecificBeanImplementation) oTipousuarioDao.get(oResultSet.getInt("id_tipousuario"), expand - 1);
//            this.setObj_tipousuario(oTipousuarioBean);
//        } else {
//            this.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
//        }
//
//        return this;
//    }
}
