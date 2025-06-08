package com.homework;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {
    private AtomicInteger count = new AtomicInteger(0);


    @Override
    public int incrementVisitCount() {
        try {
            count.incrementAndGet();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return count.get();
    }

    @Override
    public int getVisitCount() {
        return this.count.get();
    }

}
