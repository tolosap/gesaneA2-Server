/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.helper;

public class FilterBeanHelper {

    private String link;
    private String field;
    private String operation;
    private String value;

    public FilterBeanHelper(String operator, String field, String operation, String value) {
        this.link = operator;
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public FilterBeanHelper() {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
