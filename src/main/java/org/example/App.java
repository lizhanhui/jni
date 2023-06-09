package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Hello world!
 */
public class App {
    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) throws Exception {
        final AtomicLong total = new AtomicLong(0);
        final AtomicInteger count = new AtomicInteger(0);
        App app = new App();
        System.out.println("Start to call native method");

        Executors.newScheduledThreadPool(1).schedule(new Runnable() {
            public void run() {
                long t = total.getAndSet(0);
                long c = count.getAndSet(0);
                if (c > 0) {
                    System.out.println("AVG: " + (t / c) + "ns");
                }
            }
        }, 1, TimeUnit.SECONDS);

        while (true) {
            long start = System.nanoTime();
            app.foo();
            count.incrementAndGet();
            total.addAndGet(System.nanoTime() - start);
        }
    }

    public native void foo();
}
