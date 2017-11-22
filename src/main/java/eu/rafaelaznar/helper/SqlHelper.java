/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * trolleyes-server3: Helps you to develop easily AJAX web applications
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server3
 *
 * trolleyes-server3 is distributed under the MIT License (MIT)
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
package eu.rafaelaznar.helper;

import eu.rafaelaznar.bean.helper.FilterBeanHelper;
import static java.lang.Math.ceil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SqlHelper {

    public static String buildSqlFilter(ArrayList<FilterBeanHelper> alFilter) throws ParseException {
        String strSQLFilter = "";
        for (FilterBeanHelper oFilterBean : alFilter) {
            strSQLFilter += getFilterExpression(oFilterBean);
        }
        return strSQLFilter;
    }

    private static String getFormatDate(String dateIn) throws ParseException {
        SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parseador.parse(dateIn);
        SimpleDateFormat formateador = new SimpleDateFormat("yyy/MM/dd");
        String fecha = formateador.format(date);
        return fecha;
    }

    private static String getFilterExpression(FilterBeanHelper temp) throws ParseException {

        switch (temp.getOperation()) {
            //operations for date ----------------------------------------------
            case "dequa": //equal 
                return temp.getLink() + " " + temp.getField() + " = '" + getFormatDate(temp.getValue()) + "' ";
            case "dnequ": //not equal
                return temp.getLink() + " " + temp.getField() + " != '" + getFormatDate(temp.getValue()) + "' ";
            case "dlowe": //lower than
                return temp.getLink() + " " + temp.getField() + " < '" + getFormatDate(temp.getValue()) + "' ";
            case "dlequ": //lower or equal than
                return temp.getLink() + " " + temp.getField() + " <= '" + getFormatDate(temp.getValue()) + "' ";
            case "dgrea": //greater than
                return temp.getLink() + " " + temp.getField() + " > '" + getFormatDate(temp.getValue()) + "' ";
            case "dgequ": //greater or equal than
                return temp.getLink() + " " + temp.getField() + " >= '" + getFormatDate(temp.getValue()) + "' ";
            //operations for strings -------------------------------------------
            case "sequa": //equal for strings
                return temp.getLink() + " " + temp.getField() + " = '" + temp.getValue() + "' ";
            case "snequ": //not equal for strings
                return temp.getLink() + " " + temp.getField() + " != '" + temp.getValue() + "' ";
            case "slike": //like
                return temp.getLink() + " " + temp.getField() + " LIKE '%" + temp.getValue() + "%' ";
            case "snlik": //not like
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '%" + temp.getValue() + "%' ";
            case "sstar": //starts with
                return temp.getLink() + " " + temp.getField() + " LIKE '" + temp.getValue() + "%' ";
            case "snsta": //not starts with
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '" + temp.getValue() + "%' ";
            case "sends": //ends with
                return temp.getLink() + " " + temp.getField() + " LIKE '%" + temp.getValue() + "' ";
            case "snend": //not ends with
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '%" + temp.getValue() + "' ";
            //operations for numbers -------------------------------------------
            case "nequa": //equal for numbers
                return temp.getLink() + " " + temp.getField() + " = " + temp.getValue() + " ";
            case "nnequ": //not equal for numbers
                return temp.getLink() + " " + temp.getField() + " != " + temp.getValue() + " ";
            case "nlowe": //lower than
                return temp.getLink() + " " + temp.getField() + " < " + temp.getValue() + " ";
            case "nlequ": //lower or equal than
                return temp.getLink() + " " + temp.getField() + " <= " + temp.getValue() + " ";
            case "ngrea": //greater than
                return temp.getLink() + " " + temp.getField() + " > " + temp.getValue() + " ";
            case "ngequ": //greater or equal than
                return temp.getLink() + " " + temp.getField() + " >= " + temp.getValue() + " ";
            //operations for boolean -------------------------------------------
            case "bequa": //equal for boolean
                return temp.getLink() + " " + temp.getField() + " = " + temp.getValue() + " ";
            //------------------------------------------------------------------
            default:
                throw new Error("Filter expression malformed. Operator not valid.");
        }
    }

    public static String buildSqlLimit(Long intTotalRegs, Integer intRegsPerPage, Integer intPageNumber) {
        String SQLLimit = "";
        if (intRegsPerPage > 0 && intRegsPerPage < 10000) {
            if (intPageNumber > 0 && intPageNumber <= (ceil((intTotalRegs / intRegsPerPage) + 1))) {
                SQLLimit = " LIMIT " + (intPageNumber - 1) * intRegsPerPage + " , " + intRegsPerPage;
            } else {
                SQLLimit = " LIMIT 0 ";
            }
        }
        return SQLLimit;
    }

    public static String buildSqlOrder(LinkedHashMap<String, String> hmOrder) {
        String strSQLOrder = "";
        if (hmOrder != null) {
            for (Map.Entry<String, String> entry : hmOrder.entrySet()) {
                strSQLOrder += entry.getKey();
                strSQLOrder += " ";
                strSQLOrder += entry.getValue();
                strSQLOrder += ",";
            }
            strSQLOrder = " ORDER BY " + strSQLOrder.substring(0, strSQLOrder.length() - 1);
        }
        return strSQLOrder;
    }

}
