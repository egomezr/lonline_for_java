package org.dynamicloud.lonline.processor;

import org.dynamicloud.api.DynamicProvider;
import org.dynamicloud.api.DynamicProviderImpl;
import org.dynamicloud.api.RecordCredential;
import org.dynamicloud.api.model.RecordModel;
import org.dynamicloud.concurrency.ThreadPool;
import org.dynamicloud.exception.DynamicloudProviderException;
import org.dynamicloud.lonline.LonlineLog;
import org.dynamicloud.lonline.LonlineSetting;
import org.dynamicloud.util.LonlineUtil;

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
 * <code>LonlineProcessorImpl</code>
 * <p/>
 * This class receives the {@link org.dynamicloud.lonline.LonlineSetting} to setup Lonline.  This class is instantiated by {@link org.dynamicloud.lonline.appender.LonlineAppender}.
 * <p/>
 * <h2><strong>To use this class directly without log4j appenders, follow the next example:</strong></h2>
 * <pre>
 *      LonlineSetting setting = new LonlineSetting() {
 *          public long getModelIdentifier(){return 789;}
 *          public String getCsk(){return "csk#123";}
 *          public String getAci(){return "aci#456";}
 *          public boolean isWarning(){return true;}
 *          public int getReportLimit(){return 100;}
 *          public boolean isBacktrace(){return true;}
 *          public boolean isAsync(){return true;}
 *      }
 *
 *      LonlineProcessor processor = new LonlineProcessorImpl(setting);
 *
 *      LonlineLog log = new LonlineLog("error", "Custom log", "No trace");
 *
 *      processor.process(log);
 * <pre/>
 *
 * @author Eleazar Gomez
 */
public class LonlineProcessorImpl implements LonlineProcessor {
    private final LonlineSetting settings;

    public LonlineProcessorImpl(LonlineSetting settings) {
        this.settings = settings;
    }

    /**
     * This methods will call the Dynamicloud's service to save a record in a model
     *
     * @param log log to be sent to Dynamicloud
     */
    @Override
    public void process(final LonlineLog log) {
        if (settings.isAsync()) {
            ThreadPool.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    executeService(log);
                }
            });
        } else {
            LonlineUtil.warnIt(settings, "async property is false, this is a negative impact to your system's throughput.");

            executeService(log);
        }
    }

    /**
     * This method executes the service to store the log in Dynamicloud
     * @param log log to store in Dynamicloud
     */
    private void executeService(LonlineLog log) {
        RecordCredential credentials = new RecordCredential(settings.getCsk(), settings.getAci());
        DynamicProvider<LonlineLog> provider = new DynamicProviderImpl<LonlineLog>(credentials);

        try {
            provider.saveRecord(new RecordModel(settings.getModelIdentifier()), log);
        } catch (DynamicloudProviderException e) {
            LonlineUtil.warnIt(settings, e.getMessage());
        }
    }
}