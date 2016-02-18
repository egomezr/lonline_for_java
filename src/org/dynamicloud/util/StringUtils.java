package org.dynamicloud.util;

/**
 * Copyright (c) 2016 Dynamicloud
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p/>
 * <code>StringUtils</code>
 * <p/>
 * This class provides utilities methods to handle strings.
 *
 * @author Eleazar Gomez
 */
public class StringUtils {
    /**
     * Just a simple space
     */
    public static final String SPACE = "";

    /**
     * Joins the elements of one array by comma.
     *
     * @param array array to join
     * @return String with the element's toString result separated by comma
     */
    public static String join(Object[] array) {
        return join(array, ",");
    }

    /**
     * Joins the elements of one array by separator param.
     *
     * @param array array to join
     * @return String with the element's toString result separated by separator param
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return "";
        }

        if (separator == null) {
            separator = ",";
        }

        StringBuilder buf = new StringBuilder(array.length * 16);

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * Validates if string if empty or null
     *
     * @param string string to evaluate
     * @return true if this string is empty or null
     */
    public static boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }

    /**
     * Will return a capitalized string
     *
     * @param string string to capitalize
     * @return capitalized string
     */
    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * Will return a uncapitalized string
     *
     * @param string string to unCapitalize
     * @return uncapitalized string
     */
    public static String unCapitalize(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    /**
     * This method will escape the special characters to unicode form.
     *
     * @param input input to escape
     * @return escaped string
     */
    public static String escape2Unicode(String input) {
        StringBuilder b = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c >= 128)
                b.append("\\u").append(String.format("%04X", (int) c));
            else
                b.append(c);
        }

        return b.toString();
    }
}