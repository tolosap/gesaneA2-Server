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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;

public class ParameterHelper {

    public static String prepareCamelCaseObject(HttpServletRequest request) {
        String result = null;
        if (request.getParameter("ob") == null) {
            result = null;
        } else {
            result = Character.toUpperCase(request.getParameter("ob").charAt(0)) + request.getParameter("ob").substring(1);
        }
        return result;
    }

    public static LinkedHashMap<String, String> getOrderParams(String strOrder) {
        LinkedHashMap<String, String> oHMOrder = new LinkedHashMap<String, String>();
        if (strOrder != null && strOrder.length() > 0) {
            String[] arrOrderSplit1 = strOrder.split(" ");
            for (String s : arrOrderSplit1) {
                String[] arrOrderSplit2 = s.split(",");
                if (s.contains(",")) {
                    if ("asc".equalsIgnoreCase(arrOrderSplit2[1])) {
                        oHMOrder.put(arrOrderSplit2[0], "ASC");
                    } else {
                        oHMOrder.put(arrOrderSplit2[0], "DESC");
                    }
                } else {
                    oHMOrder.put(arrOrderSplit2[0], "ASC");
                }
            }
        } else {
            oHMOrder = null;
        }
        return oHMOrder;
    }

    public static ArrayList<FilterBeanHelper> getFilterParams(String strFilter) {
        ArrayList<FilterBeanHelper> oFilterBean = new ArrayList<>();
        if (strFilter != null && strFilter.length() > 0) {
            String[] arrFilterSplit1 = strFilter.split(" ");
            for (String s : arrFilterSplit1) {
                String[] arrFilterSplit2 = s.split(",");
                if (arrFilterSplit2.length == 4) {
                    FilterBeanHelper oFilterBeanHelper = new FilterBeanHelper();
                    oFilterBeanHelper.setLink(arrFilterSplit2[0]);
                    oFilterBeanHelper.setField(arrFilterSplit2[1]);
                    oFilterBeanHelper.setOperation(arrFilterSplit2[2]);
                    oFilterBeanHelper.setValue(arrFilterSplit2[3]);
                    oFilterBean.add(oFilterBeanHelper);
                }
            }
        }
        return oFilterBean;
    }
}
