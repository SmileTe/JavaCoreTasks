package com.homework;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {
    private final SiteVisitCounter siteVisitCounter;
    private final List<Thread> threads = new ArrayList<>();
    private long startTime;
    private long endTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }


    public void waitUntilAllVisited() throws InterruptedException {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        endTime = System.currentTimeMillis();
    }

    public long getTotalTimeOfHandling() {
        //Возвращает общее время обработки всех потоков в секундах.
        return endTime - startTime;
    }
}
