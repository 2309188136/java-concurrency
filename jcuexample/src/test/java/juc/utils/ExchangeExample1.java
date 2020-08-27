package juc.utils;

import java.util.concurrent.Exchanger;

public class ExchangeExample1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                String result = exchanger.exchange("Message from " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " got result value: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " completed!");
        }).start();
    }
}
