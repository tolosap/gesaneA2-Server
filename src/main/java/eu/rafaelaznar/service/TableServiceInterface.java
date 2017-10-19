/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.service;

import eu.rafaelaznar.bean.ReplyBean;

public interface TableServiceInterface {

    public ReplyBean get() throws Exception;

    public ReplyBean set() throws Exception;

    public ReplyBean remove() throws Exception;

}
