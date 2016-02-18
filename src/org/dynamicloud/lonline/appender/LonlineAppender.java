package org.dynamicloud.lonline.appender;

import org.apache.log4j.spi.LoggingEvent;
import org.dynamicloud.lonline.LonlineEvent;
import org.dynamicloud.lonline.LonlineLog;
import org.dynamicloud.lonline.processor.LonlineProcessor;
import org.dynamicloud.lonline.LonlineSetting;
import org.dynamicloud.util.StringUtils;

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
 * <code>LonlineAppender</code>
 * <p/>
 * This class extends {@link org.dynamicloud.lonline.appender.AppenderSkeletonAdapter} and implements the append method from log4j AppenderSkeleton class.
 * The append method will be called by log4j and this way, log4j will validate if a log must be printed according to the established level.
 *
 * @author Eleazar Gomez
 */
public class LonlineAppender extends AppenderSkeletonAdapter implements LonlineSetting {
    private LonlineProcessor processor;

    public LonlineAppender() {
        processor = LonlineProcessor.Impl.getInstance(this);
    }

    /**
     * This methods receives the LoggingEvent either from log4j or {@link org.dynamicloud.lonline.Lonline} class.
     * If the caller is {@link org.dynamicloud.lonline.LonlineLog} the LoggingEvent is instance of {@link org.dynamicloud.lonline.LonlineEvent}.  This situation occurs when addition information will be sent to Dynamicloud
     *
     * @param loggingEvent LoggingEvent with the level, text and trace.  If this loggingEvent is instance of {@link org.dynamicloud.lonline.LonlineEvent} then an additionalInformation attribute is included.
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {
        String level = loggingEvent.getLevel().toString().toLowerCase();
        String text = loggingEvent.getMessage().toString();
        String trace = StringUtils.SPACE;

        if (loggingEvent.getThrowableInformation() != null) {
            trace = StringUtils.join(loggingEvent.getThrowableInformation().getThrowable().getStackTrace(), "\n");
        }

        LonlineLog log;
        if (loggingEvent instanceof LonlineEvent) {
            log = ((LonlineEvent) loggingEvent).getAdditionalInformation();

            log.setBacktrace(trace);
            log.setLevel(level);
            log.setText(text);
        } else {
            log = new LonlineLog(level, text, trace);
        }

        processor.process(log);
    }
}