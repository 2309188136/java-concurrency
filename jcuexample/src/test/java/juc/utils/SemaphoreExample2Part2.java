package juc.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2Part2 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 3; i++){
            new Thread (()->{
                System.out.println(Thread.currentThread().getName() + " about enter.");
                try {
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + " acquired a permit form semaphore");
                    TimeUnit.SECONDS.sleep(5 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(1);
                }
                System.out.println(Thread.currentThread().getName() + " exited.");
            }, "thread-"+ i).start();
        }

        while (!Thread.currentThread().isInterrupted()){
            System.out.println("availiable permits: " + semaphore.availablePermits());
            System.out.println("estimate number of threads waiting to aquire : " + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
