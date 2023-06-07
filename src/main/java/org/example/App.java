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

    public static void main( String[] args )
    {
        long total = 0;
        int count = 1000000;
        App app = new App();
        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            app.foo();
            total += System.nanoTime() - start;
        }
        System.out.println("AVG: " + total / count + "ns");
    }

    public native void foo();
}
