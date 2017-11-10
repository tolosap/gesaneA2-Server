/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * trolleyes-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server
 * 
 * trolleyes-server is distributed under the MIT License (MIT)
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

public class EstadoHelper {

    public static enum Tipo_estado {
        Debug,
        Production
    };

    public static Tipo_estado getTipo_estado() {
        return Tipo_estado.Debug;
    }

    public static String getVersion() {
        return "03";
    }

    public static String getFecha() {
        return "17/10/2017";
    }

    public static String getAnyo() {
        return "2017";
    }

    public static String getCurso() {
        return "2017-2018";
    }

    public static String getAutor() {
        return "Rafael Aznar";
    }

    public static String getMailAutor() {
        return "rafaaznar{at}gmail{dot}com";
    }

    public static String getLicenciaLink() {
        return "<a href=\"https://opensource.org/licenses/MIT\">MIT License</a>";
    }

    public static int getDelay() {
        return 0;
    }
}
