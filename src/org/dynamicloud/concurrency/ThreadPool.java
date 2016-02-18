package org.dynamicloud.concurrency;

import java.util.LinkedList;

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
 * This is a basic ThreadPool to enqueue tasks in Lonline.  This ThreadPool is used when Lonline is configured with async behaviour.
 * <p/>
 *
 * @author Eleazar Gomez
 */
public class ThreadPool {
    private static ThreadPool pool;

    private BlockingQueue queue = new BlockingQueue();
    private boolean closed = true;

    private int poolSize = Runtime.getRuntime().availableProcessors() + 2;

    public static ThreadPool getThreadPool() {
        if (pool == null) {
            pool = new ThreadPool();

            pool.start();
        }

        return pool;
    }

    synchronized private void start() {
        if (!closed) {
            throw new IllegalStateException("Pool already started.");
        }
        closed = false;
        for (int i = 0; i < poolSize; ++i) {
            new PooledThread().start();
        }
    }

    synchronized public void execute(Runnable job) {
        if (closed) {
            throw new PoolClosedException();
        }
        queue.enqueue(job);
    }

    private class PooledThread extends Thread {
        public void run() {
            while (true) {
                Runnable job = (Runnable) queue.dequeue();
                if (job == null) {
                    break;
                }
                try {
                    job.run();
                } catch (Throwable t) {
                    // ignore
                }
            }
        }
    }

    private static class PoolClosedException extends RuntimeException {
        PoolClosedException() {
            super("Pool closed.");
        }
    }
}

class BlockingQueue {
    private final LinkedList<Runnable> list = new LinkedList<Runnable>();

    synchronized public void enqueue(Runnable o) {
        list.add(o);
        notify();
    }

    synchronized public Object dequeue() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // ignore
            }
        }
        if (list.size() == 0) {
            return null;
        }
        return list.removeFirst();
    }
}