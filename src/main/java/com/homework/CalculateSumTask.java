package com.homework;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {
    private List<Integer> numbers;
    private String nameTask;

    public CalculateSumTask(List<Integer> numbers, String nameTask) {
        this.nameTask = nameTask;
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println(nameTask + " " + Thread.currentThread().getName());

        Integer sum = 0;
        for (Integer number :
                numbers) {
            sum = sum + number;
        }

        Thread.sleep(200);
        return sum;
    }
}
