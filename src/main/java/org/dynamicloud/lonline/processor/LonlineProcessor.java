package org.dynamicloud.lonline.processor;

import org.dynamicloud.lonline.LonlineLog;
import org.dynamicloud.lonline.LonlineSetting;

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
 * <code>LonlineProcessor</code>
 * <p/>
 * This interface provides the method's declarations to implement a processor of {@link org.dynamicloud.lonline.LonlineLog}
 *
 * @author Eleazar Gomez
 */
public interface LonlineProcessor {
    /**
     * This methods will call the Dynamicloud's service to save a record in a model
     *
     * @param log log to be sent to Dynamicloud
     */
    public void process(LonlineLog log);

    /**
     * This class provides a static method to instantiate a {@link org.dynamicloud.lonline.processor.LonlineProcessorImpl} object.
     */
    public class Impl {
        public static LonlineProcessor getInstance(LonlineSetting settings) {
            return new LonlineProcessorImpl(settings);
        }
    }
}