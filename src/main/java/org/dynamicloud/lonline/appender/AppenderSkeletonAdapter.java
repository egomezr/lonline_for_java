package org.dynamicloud.lonline.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

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
 * <code>AppenderSkeletonAdapter</code>
 * <p/>
 * This class is an adapter and implements AppenderSkeleton to follow the same structure of log4j appender.
 * It has the necessary methods to allow set settings from log4j file.
 *
 * @author Eleazar Gomez
 */
public class AppenderSkeletonAdapter extends AppenderSkeleton {
    private long modelIdentifier;
    private String csk;
    private String aci;
    private boolean warning;
    private int reportLimit;
    private boolean backtrace;
    private boolean async;

    /**
     * This methods receives the LoggingEvent either from log4j or {@link org.dynamicloud.lonline.Lonline} class.
     * If the caller is {@link org.dynamicloud.lonline.LonlineLog} the LoggingEvent is instance of {@link org.dynamicloud.lonline.LonlineEvent}.  This situation occurs when addition information will be sent to Dynamicloud
     *
     * @param loggingEvent LoggingEvent with the level, text and trace.  If this loggingEvent is instance of {@link org.dynamicloud.lonline.LonlineEvent} then an additionalInformation attribute is included.
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {

    }

    /**
     * Release any resources allocated within the appender such as file
     * handles, network connections, etc.
     * <p/>
     * <p>It is a programming error to append to a closed appender.
     *
     * @since 0.8.4
     */
    @Override
    public void close() {

    }

    /**
     * Configurators call this method to determine if the appender
     * requires a layout. If this method returns <code>true</code>,
     * meaning that layout is required, then the configurator will
     * configure an layout using the configuration information at its
     * disposal.  If this method returns <code>false</code>, meaning that
     * a layout is not required, then layout configuration will be
     * skipped even if there is available layout configuration
     * information at the disposal of the configurator..
     * <p/>
     * <p>In the rather exceptional case, where the appender
     * implementation admits a layout but can also work without it, then
     * the appender should return <code>true</code>.
     *
     * @since 0.8.4
     */
    @Override
    public boolean requiresLayout() {
        return false;
    }

    /**
     * Returns the model identifier in Dynamicloud
     *
     * @return model id
     */
    public long getModelIdentifier() {
        return modelIdentifier;
    }

    /**
     * Sets the model identifier from log4j file
     *
     * @param modelIdentifier model id
     */
    public void setModelIdentifier(long modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    /**
     * Returns the Client Secret Key in Dynamicloud
     *
     * @return client secret key
     */
    public String getCsk() {
        return csk;
    }

    /**
     * Sets the client secret key from log4j file
     *
     * @param csk client secret key
     */
    public void setCsk(String csk) {
        this.csk = csk;
    }

    /**
     * Returns the API client id
     *
     * @return API client id
     */
    public String getAci() {
        return aci;
    }

    /**
     * Sets the API client id from log4j file
     *
     * @param aci API client id
     */
    public void setAci(String aci) {
        this.aci = aci;
    }

    /**
     * Returns true is Lonline prints warnings
     *
     * @return true to print warnings
     */
    public boolean isWarning() {
        return warning;
    }

    /**
     * Sets warning value from log4j file
     *
     * @param warning value
     */
    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    /**
     * Returns the limit of logs to be fetched from Dynamicloud
     *
     * @return report limit value
     */
    public int getReportLimit() {
        return reportLimit;
    }

    /**
     * Sets the record limit value from log4j file
     *
     * @param reportLimit limit value
     */
    public void setReportLimit(int reportLimit) {
        this.reportLimit = reportLimit;
    }

    /**
     * Returns true is BackTrace will be sent to Dynamicloud
     *
     * @return backtrace value
     */
    public boolean isBacktrace() {
        return backtrace;
    }

    /**
     * Sets the backtrace value from log4j file
     *
     * @param backtrace value
     */
    public void setBacktrace(boolean backtrace) {
        this.backtrace = backtrace;
    }

    /**
     * Returns true if Lonline will call Dynamicloud's services asynchronously
     *
     * @return async value
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * Sets the async value from log4j file
     *
     * @param async value
     */
    public void setAsync(boolean async) {
        this.async = async;
    }
}