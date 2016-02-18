package org.dynamicloud.lonline;

import org.dynamicloud.api.annotation.Bind;
import org.dynamicloud.lonline.LonlineLog;

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
 * <code>AdditionalInformation.java</>
 * <p/>
 * This class is a helper to send additional information and not only level, trace and backtrace
 *
 * @author Eleazar Gomez
 */
public class AdditionalInformation extends LonlineLog {
    private String financialModule;

    /**
     * Default constructor
     */
    public AdditionalInformation() {

    }

    public AdditionalInformation(String module) {
        this.financialModule = module;
    }

    @Bind(field = "lonlinemodule")
    public void setFinancialModule(String module) {
        this.financialModule = module;
    }

    public String getFinancialModule() {
        return this.financialModule;
    }
}