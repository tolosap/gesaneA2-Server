/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;

@MetaObjectBeanInterface(
        TableName = "grupo",
        SingularDescription = "Grupo",
        PluralDescription = "Grupos",
        Icon = "fa fa-users",
        Type = EnumHelper.SourceType.Table
)
public class GrupoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cod.",
            LongName = "Código",
            Description = "Código del grupo",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = "[^a-z0-9-]",
            RegexHelp = "letras mayúsculas y números",
            IsForeignKeyDescriptor = true,
            MaxLength = 50
    )
    private String codigo;

    @Expose(serialize = false)
    private Integer id_curso = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Curso",
            LongName = "Curso",
            Description = "Curso",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "curso",
            Wide = 4
    )
    private MetaBeanHelper obj_curso = null;

    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Profesor",
            LongName = "Profesor",
            Description = "Profesor del curso",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "usuario",
            Wide = 4
    )
    private MetaBeanHelper obj_usuario = null;

    public GrupoSpecificBeanImplementation() {
    }

    public GrupoSpecificBeanImplementation(Integer id_grupo) {
        this.id = id_grupo;
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

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public MetaBeanHelper getObj_curso() {
        return obj_curso;
    }

    public void setObj_curso(MetaBeanHelper obj_curso) {
        this.obj_curso = obj_curso;
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

}
