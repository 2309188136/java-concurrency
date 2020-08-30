package juc.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaPhoreExample2 {
    public static void main(String[] args) {
//        testScenario1();
       //testScenario2();
        testScenario3();
    }

    private static void testScenario1(){
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 2; i++){
            new Thread (()->{
                System.out.println(Thread.currentThread().getName() + " about enter.");
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " acquired a permit form semaphore");
                    TimeUnit.SECONDS.sleep(5 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                System.out.println(Thread.currentThread().getName() + " exited.");
            }, "thread-"+ i).start();
        }
    }

    private static void testScenario2(){
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 2; i++){
            new Thread (()->{
                System.out.println(Thread.currentThread().getName() + " about enter.");
                try {
                    semaphore.acquire(2);
                    System.out.println(Thread.currentThread().getName() + " acquired a permit form semaphore");
                    TimeUnit.SECONDS.sleep(5 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(2);
                }
                System.out.println(Thread.currentThread().getName() + " exited.");
            }, "thread-"+ i).start();
        }
    }

    private static void testScenario3(){
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 2; i++){
            new Thread (()->{
                System.out.println(Thread.currentThread().getName() + " about enter.");
                try {
                    semaphore.acquire(2);
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
    }
}
