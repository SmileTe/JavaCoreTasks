package com.homework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {
    ExecutorService executorService;
    private Map<String, Integer> resultMap = new HashMap<>();
    private AtomicInteger taskCounter = new AtomicInteger(1);
    private AtomicInteger activeTaskCount = new AtomicInteger(0);

    public DataProcessor(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public synchronized void submitTask(List<Integer> numbers) {

        String taskName = "task" + taskCounter.getAndIncrement();
        activeTaskCount.incrementAndGet();
        CalculateSumTask task = new CalculateSumTask(numbers, taskName);

        Future<Integer> futureResult = executorService.submit(() -> {
            AtomicInteger sum = new AtomicInteger(task.call());
            return Integer.valueOf(sum.get());
        });
        try {
            Integer result = futureResult.get();  // Ждём, пока задача вернёт результат
            synchronized (resultMap) {
                resultMap.put(taskName, Integer.valueOf(result));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            activeTaskCount.decrementAndGet();
        }
    }

    public int getActiveTaskCount() {
        return activeTaskCount.get();
    }

    public Optional<Integer> getResult(String taskName) {
        synchronized (resultMap) {
            if (resultMap.containsKey(taskName)) {
                return Optional.of(resultMap.get(taskName));
            }
        }
        return Optional.empty();
    }

    public void shutdown() {
        this.executorService.shutdown();
    }

}
