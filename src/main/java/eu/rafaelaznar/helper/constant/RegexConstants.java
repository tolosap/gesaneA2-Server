/*
 * Copyright (c) 2017-2018 
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & DAW students
 * 
 * GESANE: Free Open Source Health Management System
 *
 * Sources at:
 *                            https://github.com/rafaelaznar/gesane-server
 *                            https://github.com/rafaelaznar/gesane-client
 *                            https://github.com/rafaelaznar/gesane-database
 *
 * GESANE is distributed under the MIT License (MIT)
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
package eu.rafaelaznar.helper.constant;

public class RegexConstants {

    public static final String dni = "[0-9]{8,8}[A-Z]";
    public static final String dni_Help = "ocho dígitos y una letra mayúscula";
    public static final String capitalizedName = "([A-Z]{1}[a-zçñáéíóúàèò]+[\\s]*)+";
    public static final String capitalizedName_Help = "una o varias palabras que todas comienzan en mayúcula";
    public static final String nameWithEndingNumbers = "[a-z][a-z0-9]+";
    public static final String nameWithEndingNumbers_Help = "un texto en minúsculas que puede acabar en dígitos";
    public static final String email = "[a-z]+[a-z0-9._]+@[a-z]+\\.[a-z.]{2,5}"; // see \\ is \    
    public static final String email_Help = "un email válido"; // see \\ is \    
    public static final String capitalizedSentence = "[A-Z]([^\\s]*\\s?)+";
    public static final String capitalizedSentence_Help = "una frase que comienza en mayúscula";
    public static final String direction = "[A-Z]{1}[a-z0-9ºçñáéíóúàèò- ]*";
    public static final String directionSentence_Help = "una direccion que comienza en mayúscula";
}
