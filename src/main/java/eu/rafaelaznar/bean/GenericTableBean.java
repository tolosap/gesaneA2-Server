/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean;

import com.google.gson.annotations.Expose;

/**
 *
 * @author raznara
 */
public class GenericTableBean implements GenericBeanInterface{

    @Expose
    private Integer id;


    public GenericTableBean() {

    }

    public GenericTableBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
