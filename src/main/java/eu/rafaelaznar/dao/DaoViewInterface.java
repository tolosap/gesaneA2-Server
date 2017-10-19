/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.dao;

import java.util.ArrayList;


public interface DaoViewInterface<GenericBeanImplementation> {

    public Long getCount() throws Exception;

    public ArrayList<GenericBeanImplementation> getPage(int intRegsPerPag, int intPage) throws Exception;

}
