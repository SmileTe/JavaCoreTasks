package com.homework;

public class UnsynchronizedCounter implements SiteVisitCounter {

    private int count = 0;


    @Override
    public int incrementVisitCount() {
        try {
            count++;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return count;
    }

    @Override
    public int getVisitCount() {
        return this.count;
    }
}
