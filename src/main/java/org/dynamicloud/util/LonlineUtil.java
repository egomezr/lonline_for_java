package org.dynamicloud.util;

import org.dynamicloud.lonline.LonlineSetting;

import java.text.SimpleDateFormat;
import java.util.Date;

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
 * <code>LonlineUtil</code>
 * <p/>
 * This class provides global utility methods.
 */
public class LonlineUtil {
    private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    /**
     * This method prints a warning only if settings.isWarning() returns true
     *
     * @param message  message to be printed.
     * @param settings the lonline settings
     */
    public static void warnIt(LonlineSetting settings, String message) {
        if (settings.isWarning()) {
            System.out.println("[LONLINE - WARN @ " + df.format(new Date()) + "] - " + message);
        }
    }
}
