package juc.utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangeExample1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                String result = exchanger.exchange("Message from " + Thread.currentThread().getName());
//                String result =exchanger.exchange("Message from " + Thread.currentThread().getName(), 10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " got result value: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            catch (TimeoutException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + " completed!");
        },"A").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                String result = exchanger.exchange("Message from " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " got result value: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " completed!");
        },"B").start();
    }
}
