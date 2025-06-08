package com.homework;

public class VolatileCounter implements SiteVisitCounter {
    private volatile int count = 0;


    @Override
    public int incrementVisitCount() {
        try {
            this.count++;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this.count;
    }

    @Override
    public int getVisitCount() {
        return this.count;
    }
}
