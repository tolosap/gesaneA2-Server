/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * sisane-server: Helps you to develop easily AJAX web applications
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/sisane-server
 *
 * sisane-server is distributed under the MIT License (MIT)
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
import eu.rafaelaznar.dao.specificimplementation.GrupoSpecificDaoImplementation;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.helper.constant.RegexConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    @Expose(serialize = false)
    private String token;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "¿Activo?",
            LongName = "¿Usuario activo?",
            Description = "¿Usuario activo?",
            Type = EnumHelper.FieldType.Integer,
            IsRequired = true
    )
    private Boolean activo;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.alta",
            LongName = "Fecha de alta",
            Description = "Fecha de alta del usuario",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true,
            IsVisible = false
    )
    private Date fecha_alta;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "¿Validado?",
            LongName = "¿Usuario validado?",
            Description = "¿Usuario validado?",
            Type = EnumHelper.FieldType.Integer,
            IsRequired = true
    )
    private Boolean validado;

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

    @Expose(serialize = false)
    private Integer id_grupo = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Grupo",
            LongName = "Grupo del usuario",
            Description = "Grupo del usuario",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "grupo",
            Wide = 4
    )
    private MetaBeanHelper obj_grupo = null;

    @Expose(deserialize = false)
    private ArrayList<MetaBeanHelper> obj_grupos;

    @Expose(serialize = false)
    private Integer id_centro = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Centro ed.",
            LongName = "Centro educativo",
            Description = "Centro educativo del usuario",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "centro",
            Wide = 4
    )
    private MetaBeanHelper obj_centro = null;

    @Expose(serialize = false)
    private Integer id_centrosanitario = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Centro san.",
            LongName = "Centro sanitario",
            Description = "Centro sanitario del usuario",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "centrosanitario",
            Wide = 4
    )
    private MetaBeanHelper obj_centrosanitario = null;

    public UsuarioSpecificBeanImplementation() {
        this.obj_grupos = new ArrayList<>();
    }

    public UsuarioSpecificBeanImplementation(Integer id) {
        this.obj_grupos = new ArrayList<>();
        this.id = id;
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

    public void setPrimer_apellido(String primerapellido) {
        this.primer_apellido = primerapellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public Integer getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(Integer id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    public MetaBeanHelper getObj_tipousuario() {
        return obj_tipousuario;
    }

    public void setObj_tipousuario(MetaBeanHelper obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }

    public Integer getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
    }

    public MetaBeanHelper getObj_grupo() {
        return obj_grupo;
    }

    public void setObj_grupo(MetaBeanHelper obj_grupo) {
        this.obj_grupo = obj_grupo;
    }

    public Integer getId_centro() {
        return id_centro;
    }

    public void setId_centro(Integer id_centro) {
        this.id_centro = id_centro;
    }

    public MetaBeanHelper getObj_centro() {
        return obj_centro;
    }

    public void setObj_centro(MetaBeanHelper obj_centro) {
        this.obj_centro = obj_centro;
    }

    public Integer getId_centrosanitario() {
        return id_centrosanitario;
    }

    public void setId_centrosanitario(Integer id_centrosanitario) {
        this.id_centrosanitario = id_centrosanitario;
    }

    public MetaBeanHelper getObj_centrosanitario() {
        return obj_centrosanitario;
    }

    public void setObj_centrosanitario(MetaBeanHelper obj_centrosanitario) {
        this.obj_centrosanitario = obj_centrosanitario;
    }

    public ArrayList<MetaBeanHelper> getObj_grupos() {
        return obj_grupos;
    }

    public void setObj_grupos(ArrayList<MetaBeanHelper> obj_grupos) {
        this.obj_grupos = obj_grupos;
    }

    @Override
    public UsuarioSpecificBeanImplementation fill(ResultSet oResultSet, Connection oConnection, MetaBeanHelper oPuserBean_security, Integer expand) throws Exception {
        super.fill(oResultSet, oConnection, oPuserBean_security, expand);
        GrupoSpecificDaoImplementation oGrupoDao = null;
        GrupoSpecificBeanImplementation oGrupoBean = null;
        MetaBeanHelper oMetaBeanHelper = null;
        if (this.id_tipousuario == 3) { // si profesor rellenar sus grupos
            GrupoSpecificBeanImplementation oGrupo = null;
            PreparedStatement oPreparedStatement = null;
            ResultSet oResultSet2 = null;
            String strSQL = "select * from grupo where id_usuario= ?";
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, this.getId());
                oResultSet2 = oPreparedStatement.executeQuery();
                if (oResultSet2.next()) {
                    oGrupoDao = new GrupoSpecificDaoImplementation(oConnection, oPuserBean_security, "and id_usuario=" + this.getId().toString());
                    oGrupoBean = (GrupoSpecificBeanImplementation) new GrupoSpecificBeanImplementation();
                    this.getObj_grupos().add(oGrupoDao.get(oResultSet2.getInt("id"), expand - 1));
//                    oGrupo = (GrupoSpecificBeanImplementation) new GrupoSpecificBeanImplementation(this.getId_grupo()).fill(oResultSet2, oConnection, oPuserBean_security, expand - 1);
//                    this.getObj_grupos().add(oGrupo);
                }
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oResultSet2 != null) {
                    oResultSet2.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        }
        return this;
    }

}
