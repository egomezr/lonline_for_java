package org.dynamicloud.lonline.report;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.dynamicloud.api.*;
import org.dynamicloud.api.criteria.Conditions;
import org.dynamicloud.api.model.RecordModel;
import org.dynamicloud.lonline.LonlineLog;
import org.dynamicloud.lonline.LonlineSetting;
import org.dynamicloud.util.LonlineUtil;

import java.util.Date;
import java.util.Enumeration;

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
 * <code>LonlineReport</code>
 * <p/>
 * Lonline is a great tool to log your program into the cloud.
 * This module provides methods to get the stored logs from your account in Dynamicloud.
 * <p/>
 * LonlineReport.fetch executes queries provided from Dynamicloud library and will
 * fetch until 100 records by default, if you want to increase this, set the number
 * of records in report_limit (log4j file)
 * <p/>
 * Additionally, LonlineRecord provides a method to count how many logs were printed
 * in a specific time.
 * <p/>
 *
 * @author Eleazar Gomez
 */
public class LonlineReport {
    /**
     * This method fetches the logs according to level param and will be those created between from and to params.
     * The results are decrease ordered by creation.
     * <p/>
     * <strong>How to use:</strong>
     * <p/>
     * <pre>
     * LonlineReport.fetch(LonlineLevel.ERROR, new Date(), new Date(), new LonlineReportCallback() {
     *      &#64;Override
     *      public void manageLog(LonlineLog log) {
     *          System.out.println("log = " + log.getText());
     *      }
     * });
     * </pre>
     * <p/>
     *
     * @param level    level to be filtered
     * @param from     from date if null will fetch all records equal to level in Dynamicloud
     * @param to       to date
     * @param callback callback to manage logs
     */
    public static void fetch(LonlineLevel level, Date from, Date to, LonlineReportCallback callback) {
        fetch(level, from, to, null, callback);
    }

    /**
     * This method fetches the logs according to level param and will be those created between from and to params.
     * The results are decrease ordered by creation.
     * <p/>
     * <strong>How to use:</strong>
     * <p/>
     * <pre>
     * LonlineReport.fetch(LonlineLevel.ERROR, new Date(), new Date(), AdditionalInformation.class, new LonlineReportCallback() {
     *      &#64;Override
     *      public void manageLog(LonlineLog log) {
     *          System.out.println("log = " + ((AdditionalInformation) log).getText());
     *      }
     * });
     * </pre>
     * <p/>
     *
     * @param level    level to be filtered
     * @param from     from date if null will fetch all records equal to level in Dynamicloud
     * @param to       to date
     * @param clazz    this class represents the objects with additional information sent to Dynamicloud, this method will be used when the additional information is required to retrieve.
     *                 If clazz is not a LonlineLog class an IllegalArgumentException will be thrown.
     * @param callback callback to manage logs
     */
    public static void fetch(LonlineLevel level, Date from, Date to, Class clazz, LonlineReportCallback callback) {
        Enumeration allAppenders = Logger.getRootLogger().getAllAppenders();

        while (allAppenders.hasMoreElements()) {
            Appender appender = (Appender) allAppenders.nextElement();
            if (appender instanceof LonlineSetting) {
                LonlineSetting settings = (LonlineSetting) appender;

                RecordCredential credentials = new RecordCredential(settings.getCsk(), settings.getAci());
                DynamicProvider<LonlineLog> provider = new DynamicProviderImpl<LonlineLog>(credentials);

                RecordModel recordModel = new RecordModel(settings.getModelIdentifier());

                if (clazz != null && !LonlineLog.class.isAssignableFrom(clazz)) {
                    throw new IllegalArgumentException("The passed class must implement LonlineLog interface.");
                }

                recordModel.setBoundClass(clazz == null ? LonlineLog.class : clazz);

                Query<LonlineLog> query = provider.createQuery(recordModel);

                try {
                    if (from != null && to == null) {
                        LonlineUtil.warnIt(settings, "LonlineReport found 'Date to' as null, LonlineReport will use today's date.");
                        to = new Date();
                    }

                    RecordResults<LonlineLog> results;
                    if (from == null) {
                        results = query.add(Conditions.equals("lonlinelevel", level.getLevel())).orderBy("id").desc().list();
                    } else {
                        results = query.add(Conditions.and(Conditions.equals("lonlinelevel",
                                level.getLevel()), Conditions.between("added_at", from, to))).orderBy("id").desc().list();
                    }
                    handleResults(settings, query, callback, results, results.getFastReturnedSize());
                } catch (Exception e) {
                    LonlineUtil.warnIt(settings, e.getMessage());
                }

                return;
            }
        }

        throw new IllegalStateException("According to your log4j configuration, the LonlineAppender is not assigned to rootLogger.");
    }

    /**
     * This method counts the logs between a specific time according to 'from and to' params
     * <p/>
     * <strong>How to use:</strong>
     * <p/>
     * Fetch how many logs were printed from a specific time.
     * <pre>
     * count = LonlineReport.count(LonlineLevel.FATAL, new Date(), new Date());
     *
     * System.out.println("Count of logs" + count);
     * </pre>
     *
     * @param level target level
     * @param from  date from
     * @param to    date to
     * @return the count of logs regarding to target level and a specific time.
     */
    public static long count(LonlineLevel level, Date from, Date to) {
        Enumeration allAppenders = Logger.getRootLogger().getAllAppenders();

        while (allAppenders.hasMoreElements()) {
            Appender appender = (Appender) allAppenders.nextElement();
            if (appender instanceof LonlineSetting) {
                LonlineSetting settings = (LonlineSetting) appender;

                RecordCredential credentials = new RecordCredential(settings.getCsk(), settings.getAci());
                DynamicProvider<LonlineLog> provider = new DynamicProviderImpl<LonlineLog>(credentials);

                RecordModel recordModel = new RecordModel(settings.getModelIdentifier());
                recordModel.setBoundClass(LonlineLog.class);

                Query<LonlineLog> query = provider.createQuery(recordModel);
                query.setProjection("count(*) count");

                try {
                    if (from != null && to == null) {
                        LonlineUtil.warnIt(settings, "LonlineReport found 'Date to' as null, LonlineReport will use today's date.");
                        to = new Date();
                    }

                    RecordResults<LonlineLog> results;
                    if (from == null) {
                        results = query.add(Conditions.equals("lonlinelevel", level.getLevel())).orderBy("id").desc().list();
                    } else {
                        results = query.add(Conditions.and(Conditions.equals("lonlinelevel",
                                level.getLevel()), Conditions.between("added_at", from, to))).orderBy("id").desc().list();
                    }

                    if (results.getFastReturnedSize() > 0) {
                        return results.getRecords().get(0).getCount();
                    }

                    return 0L;

                } catch (Exception e) {
                    LonlineUtil.warnIt(settings, e.getMessage());
                }

                break;
            }
        }

        throw new IllegalStateException("According to your log4j configuration, the LonlineAppender is not assigned to rootLogger.");
    }

    /**
     * This method will call the manageLog method from the passed callback and will retrieve the next logs from Dynamicloud.
     *
     * @param settings     Lonline settings
     * @param query        RecordQuery to execute
     * @param callback     callback to manage logs
     * @param results      the result to handle
     * @param howMuchSoFar how much logs have been managed so far.
     */
    private static void handleResults(LonlineSetting settings, Query<LonlineLog> query, LonlineReportCallback callback,
                                      RecordResults<LonlineLog> results, int howMuchSoFar) {
        for (LonlineLog log : results.getRecords()) {
            callback.manageLog(log);
        }

        try {
            results = query.next();

            if (results.getFastReturnedSize() > 0) {
                handleResults(settings, query, callback, results, howMuchSoFar + results.getFastReturnedSize());
            }
        } catch (Exception e) {
            LonlineUtil.warnIt(settings, e.getMessage());
        }
    }
}