package com.homework;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private Integer count = 0;

    @Override
    public int incrementVisitCount() {
        synchronized (this) {
            try {
                this.count++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return this.count;
    }

    @Override
    public int getVisitCount() {
        return this.count;
    }
}


