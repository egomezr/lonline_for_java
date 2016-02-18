package org.dynamicloud.lonline;

import org.dynamicloud.api.BoundInstance;
import org.dynamicloud.api.annotation.Bind;

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
 * <code>LonlineLog.java</code>
 * <p/>
 * This class is a helper to send additional information and not only level, trace and backtrace
 * LonlineLog has three mandatory attributes (level, text and backtrace)
 * To send more information about your log, yo need to extend from this class and add the necessary attributes
 * These additional attributes must exist in your model as fields.<br><br>
 * <p/>
 * <h2><strong>How to use Lonline:</strong></h2>
 * <pre>
 *      class AdditionalInformation extends LonlineLog {
 *           private String module;
 *
 *           //This annotation binds Module attribute with lonlinemodule field in your model.
 *           &#64;Bind(field = "lonlinemodule")
 *           public void setModule(String module) {
 *               this.module = module;
 *           }
 *
 *           public String getModule() {
 *               return this.module;
 *           }
 *      }
 * </pre>
 *
 * @author Eleazar Gomez
 */
public class LonlineLog implements BoundInstance {
    private String level;
    private String text;
    private String backtrace;
    private Long count;
    private Date when;
    private Long recordId;

    /**
     * Default constructor
     */
    public LonlineLog() {

    }

    /**
     * This constructor instantiates a LonlineLog objecto with the mandatory attributes
     *
     * @param level     level of this log
     * @param text      text of this log
     * @param backtrace bactrace of this log
     */
    public LonlineLog(String level, String text, String backtrace) {
        this.level = level;
        this.text = text;
        this.backtrace = backtrace;
    }

    /**
     * @return the level of this log
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the level for this log
     *
     * @param level new level
     */
    @Bind(field = "lonlinelevel")
    final public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the text of this log
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text for this log
     *
     * @param text new text
     */
    @Bind(field = "lonlinetext")
    final public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the backtrace of this log
     */
    public String getBacktrace() {
        return backtrace;
    }

    /**
     * Sets the backtrace from this log
     *
     * @param backtrace new backtrace
     */
    @Bind(field = "lonlinetrace")
    final public void setBacktrace(String backtrace) {
        this.backtrace = backtrace;
    }

    /**
     * @return the count of logs
     */
    final public Long getCount() {
        return count;
    }

    /**
     * Sets the count of a query.  This method is a helper to fetch the count of logs in Dynamicloud
     * @param count count of logs
     */
    @Bind(field = "count")
    final public void setCount(Long count) {
        this.count = count;
    }

    /**
     * @return the creation time of logs
     */
    public final Date getWhen() {
        return when;
    }

    /**
     * Sets the time when a log was created.
     * @param when date time
     */
    @Bind(field = "added_at")
    public final void setWhen(Date when) {
        this.when = when;
    }

    /**
     * Returns the record id in dynamicloud server.
     *
     * @return record id
     */
    @Override
    public Number getRecordId() {
        return recordId;
    }

    /**
     * Sets the record id from Dynamicloud
     *
     * @param number record id
     */
    @Override
    public void setRecordId(Number number) {
        this.recordId = number.longValue();
    }

    /**
     * A simple to string method to debug data
     *
     * @return a string with data
     */
    @Override
    public String toString() {
        return "LonlineLog{" +
                "level='" + level + '\'' +
                ", text='" + text + '\'' +
                ", backtrace='" + backtrace + '\'' +
                '}';
    }
}
