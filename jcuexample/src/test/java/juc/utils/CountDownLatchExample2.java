package juc.utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(){
            @Override
            public void run() {
                System.out.println("Do some initial work");
                try {
                    Thread.sleep(1000);
                    countDownLatch.await();
                    System.out.println("Do some other work");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("Asynchronously prepare for data");
                    Thread.sleep(2000);
                    System.out.println("data-prepare completes");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}
