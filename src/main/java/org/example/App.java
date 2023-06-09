package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    static {
        System.loadLibrary("native");
    }

    public static void main( String[] args ) throws Exception
    {
        AtomicLong total = new AtomicLong(0);
        AtomicInteger count = new AtomicInteger(0);
        App app = new App();
        System.out.println("Start to call native method");
        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            app.foo();
            total += System.nanoTime() - start;
        }
        System.out.println("AVG: " + total / count + "ns");
        while (true) {
            Thread.sleep(1000);
        }
    }

    public native void foo();
}
