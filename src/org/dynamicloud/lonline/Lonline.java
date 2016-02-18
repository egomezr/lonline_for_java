package org.dynamicloud.lonline;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
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
 * <code>Lonline</code>
 * <p/>
 * This is the main class to log your program, Lonline class extends log4j Logger and follows the same behaviour
 * to avoid changes in your code.
 * <p/>
 * This class adds a feature to log more than only text and level, if you want to log additional information
 * instantiate the logger as Lonline and you will be able to send additional information to Dynamicloud.<br><br>
 * <p/>
 * <h2><strong>How to use Lonline:</strong></h2>
 * <pre>
 *      // As you see, the logger continues as a log4j Logger instance and this way any changes are needed.<br>
 *      Logger logger = Lonline.getLogger(MyClass.class);<br>
 *      logger.fatal("The app has Crashed!", new FatalException("My goodness"));
 *      // Without exception, just a debug log.
 *      logger.debug("Creating new object from MyClass");
 * </pre>
 * <h4>The <strong>additionalInformation</strong> object is a LonlineLog object.  For further information go to LonlineLog class documentation {@link org.dynamicloud.lonline.LonlineLog}</h4>
 * <pre>
 *      //If you want to send additional information:
 *      // Just Initialize the logger as Lonline
 *      Lonline logger = Lonline.getLogger(MyClass.class);
 *
 *      logger.fatal("The app has Crashed!", additionalInformation, new FatalException("My goodness"));
 *
 *      // Without exception, just a debug log.
 *      logger.debug("Creating new object from MyClass");
 * </pre>
 *
 * @author Eleazar Gomez
 */
public class Lonline extends Logger {
    private static final String FQCN = Logger.class.getName();

    protected Lonline(String name) {
        super(name);
    }

    /**
     * Returns a Lonline logger according className
     *
     * @param className className
     * @return Lonline logger
     */
    static public Lonline getLogger(String className) {
        return (Lonline) LogManager.getLogger(className, new LoggerFactory() {
            @Override
            public Logger makeNewLoggerInstance(String name) {
                return new Lonline(name);
            }
        });
    }

    /**
     * Returns a Lonline logger according clazz object
     *
     * @param clazz clazz object
     * @return Lonline logger
     */
    static public Lonline getLogger(Class clazz) {
        return (Lonline) LogManager.getLogger(clazz.getName(), new LoggerFactory() {
            @Override
            public Logger makeNewLoggerInstance(String name) {
                return new Lonline(name);
            }
        });
    }

    /**
     * Execute the trace and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void trace(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.TRACE_INT)) {
            return;
        }

        customLog(message, Level.TRACE, additionalInformation, null);
    }

    /**
     * Execute the trace and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void trace(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.TRACE_INT)) {
            return;
        }

        customLog(message, Level.TRACE, additionalInformation, t);
    }

    /**
     * Execute the debug and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void debug(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.DEBUG_INT)) {
            return;
        }

        customLog(message, Level.DEBUG, additionalInformation, null);
    }

    /**
     * Execute the debug and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void debug(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.DEBUG_INT)) {
            return;
        }

        customLog(message, Level.DEBUG, additionalInformation, t);
    }

    /**
     * Execute the error and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void error(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.ERROR_INT)) {
            return;
        }

        customLog(message, Level.ERROR, additionalInformation, null);
    }

    /**
     * Execute the error and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void error(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.ERROR_INT)) {
            return;
        }

        customLog(message, Level.ERROR, additionalInformation, t);
    }

    /**
     * Execute the fatal and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void fatal(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.FATAL_INT)) {
            return;
        }

        customLog(message, Level.FATAL, additionalInformation, null);
    }

    /**
     * Execute the fatal and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void fatal(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.FATAL_INT)) {
            return;
        }

        customLog(message, Level.FATAL, additionalInformation, t);
    }

    /**
     * Execute the warn and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void warn(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.WARN_INT)) {
            return;
        }

        customLog(message, Level.WARN, additionalInformation, null);
    }

    /**
     * Execute the warn and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void warn(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.WARN_INT)) {
            return;
        }

        customLog(message, Level.WARN, additionalInformation, t);
    }

    /**
     * Execute the info and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     */
    public void info(Object message, LonlineLog additionalInformation) {
        if (repository.isDisabled(Level.INFO_INT)) {
            return;
        }

        customLog(message, Level.INFO, additionalInformation, null);
    }

    /**
     * Execute the info and attaches additionalInformation
     *
     * @param message               message to log
     * @param additionalInformation additionalInformation
     * @param t                     a Throwable object to be logged
     */
    public void info(Object message, LonlineLog additionalInformation, Throwable t) {
        if (repository.isDisabled(Level.INFO_INT)) {
            return;
        }

        customLog(message, Level.INFO, additionalInformation, t);
    }

    /**
     * This method instantiates the LonlineEvent as a wrapper of LoggindEvent to attach the additionalInformation attrubute.
     *
     * @param message               message to log
     * @param level                 level of this log
     * @param additionalInformation additional information attribute
     * @param t                     a Throwable object to be logged
     */
    private void customLog(Object message, Level level, LonlineLog additionalInformation, Throwable t) {
        if (level.isGreaterOrEqual(this.getEffectiveLevel())) {
            LonlineEvent lonlineEvent = new LonlineEvent(new LoggingEvent(FQCN, this, level, message, t));
            lonlineEvent.setAdditionalInformation(additionalInformation);

            callAppenders(lonlineEvent);
        }
    }
}