package com.homework;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        testCounter(new UnsynchronizedCounter(), "UnsynchronizedCounter");
        testCounter(new VolatileCounter(), "VolatileCounter");
        testCounter(new SynchronizedBlockCounter(), "SynchronizedBlockCounter");
        testCounter(new AtomicIntegerCounter(), "AtomicIntegerCounter");
        testCounter(new ReentrantLockCounter(), "ReentrantLockCounter");

    }

    private static void testCounter(SiteVisitCounter counter, String name) {
        MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);
        visitor.visitMultithread(100);
        try {
            visitor.waitUntilAllVisited();
        } catch (InterruptedException e) {
        }
        System.out.println(name + "result: " + counter.getVisitCount());
    }
}