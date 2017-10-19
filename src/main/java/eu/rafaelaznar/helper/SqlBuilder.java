/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.helper;

import static java.lang.Math.ceil;


public class SqlBuilder {
    
    
     public static String buildSqlLimit(Long intTotalRegs, Integer intRegsPerPage, Integer intPageNumber) {
        String SQLLimit = "";
        if (intRegsPerPage > 0 && intRegsPerPage < 10000) {
            if (intPageNumber > 0 && intPageNumber <= (ceil(intTotalRegs / intRegsPerPage))) {                                                          
                SQLLimit = " LIMIT " + (intPageNumber - 1) * intRegsPerPage + " , " + intRegsPerPage;
            }
        }
        return SQLLimit;
    }
            
}
