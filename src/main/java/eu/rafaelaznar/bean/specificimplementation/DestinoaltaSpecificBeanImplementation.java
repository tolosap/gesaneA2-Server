/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean.specificimplementation;

import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;

@MetaObjectBeanInterface(
        TableName = "destinoalta",
        SingularDescription = "Destino de Alta",
        PluralDescription = "Destinos de Alta",
       // Icon = "fa fa-user-o",
        Type = EnumHelper.SourceType.Table
)
public class DestinoaltaSpecificBeanImplementation extends TableGenericBeanImplementation{
    
}
