package com.homework;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public int incrementVisitCount() {
        lock.lock();
        try {
            count++;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        return count;
    }

    @Override
    public int getVisitCount() {
        return this.count;
    }


}
