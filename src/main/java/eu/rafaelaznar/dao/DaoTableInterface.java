/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.dao;

public interface DaoTableInterface<GenericBeanImplementation> {

    public GenericBeanImplementation get(GenericBeanImplementation oBean, int intExpand) throws Exception;

    public Integer set(GenericBeanImplementation oBean) throws Exception;

    public Boolean remove(Integer id) throws Exception;

}
