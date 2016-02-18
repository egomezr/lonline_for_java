package org.dynamicloud.lonline;

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
 * <code>LonlineSetting</code>
 * <p/>
 * This interface will represent the settings from log4j properties.
 * These method provides information to setup Lonline.
 *
 * @author Eleazar Gomez
 */
public interface LonlineSetting {
    /**
     * Returns the model identifier of your model in Dynamicloud
     *
     * @return model identifier
     */
    public long getModelIdentifier();

    /**
     * Returns the client secret key of your account
     *
     * @return client secret key
     */
    public String getCsk();

    /**
     * Returns the API client id of your account
     *
     * @return API client id
     */
    public String getAci();

    /**
     * Returns true if lonline will print every warning
     *
     * @return true or false
     */
    public boolean isWarning();

    /**
     * Returns the limits of fetched logs from Dynamicloud
     *
     * @return report limit
     */
    public int getReportLimit();

    /**
     * Returns true if lonline will send the backtrace to Dynamicloud
     *
     * @return true or false
     */
    public boolean isBacktrace();

    /**
     * Returns true if lonline will execute the request asynchronously
     *
     * @return true or false
     */
    public boolean isAsync();
}