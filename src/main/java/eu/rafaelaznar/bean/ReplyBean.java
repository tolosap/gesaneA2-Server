/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.bean;

public class ReplyBean {
    private Integer code;
    private String json;

    public ReplyBean(Integer code, String json) {
        this.code = code;
        this.json = json;
    }
        
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
           
}
