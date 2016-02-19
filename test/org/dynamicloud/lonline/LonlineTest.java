package org.dynamicloud.lonline;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.dynamicloud.lonline.report.LonlineLevel;
import org.dynamicloud.lonline.report.LonlineReport;
import org.dynamicloud.lonline.report.LonlineReportCallback;

import java.util.Calendar;
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
 * This class has two main methods to test Lonline logger and Lonline reports.
 *
 * @author Eleazar Gomez
 */
public class LonlineTest extends TestCase {
    static Long index = 0L;

    /**
     * Tests Lonline logger
     */
    public void test_lonline_log() {
        Logger logger = Logger.getLogger(LonlineTest.class);

        logger.error("Testing lonline");
        logger.error("Testing lonline2");
        logger.error("Testing lonline3");
        logger.error("Testing lonline4");
        logger.error("Testing lonline5");
        logger.error("Testing lonline6");

        try {
            System.out.println("Waiting..");
            Thread.sleep(15000);
            System.out.println("Done!");
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * Tests Lonline reports
     */
    public void test_lonline_report() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -7);

        Date to = new Date();
        Date from = instance.getTime();

        LonlineReport.fetch(LonlineLevel.ERROR, from, to, AdditionalInformation.class, new LonlineReportCallback() {
            /**
             * This method will be called for each retrieved log from Dynamicloud
             * @param log retrieved log from Dynamicloud
             */
            @Override
            public void manageLog(LonlineLog log) {
                index++;
                System.out.println("log = " + log.getText());
            }
        });

        Long count = LonlineReport.count(LonlineLevel.ERROR, from, to);

        assertEquals(count, index);
    }
}