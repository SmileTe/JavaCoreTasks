package com.homework;


import java.util.Arrays;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor(10);

        dataProcessor.submitTask(Arrays.asList(7, 7, 7));
        dataProcessor.submitTask(Arrays.asList(10, 20, 30));
        dataProcessor.submitTask(Arrays.asList(10, 10, 10, 10, 10));

        for (int i = 1; i <= 3; i++) {
            Optional<Integer> result = dataProcessor.getResult("task" + i);
            System.out.println("task" + i + " сумма: " + result.orElse(null));
        }

        System.out.println("Количество текущих задач: " + dataProcessor.getActiveTaskCount());

        dataProcessor.shutdown();
    }


}


