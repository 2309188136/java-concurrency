package juc.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaPhoreExample1 {
    public static void main(String[] args) {
        final SemaphoreLock semaphoreLock = new SemaphoreLock();
        Semaphore semaphore = new Semaphore(1);
        for(int i =0; i< 2; i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    semaphoreLock.lock();
                    System.out.println(Thread.currentThread().getName() + " obtained semaphoreLock");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphoreLock.unlock();
                    System.out.println(Thread.currentThread().getName() + " released semaphoreLock");
                }
                System.out.println(Thread.currentThread().getName() + " finished");
            },String.valueOf(i)).start();
        }

    }

    static class SemaphoreLock {
        final Semaphore semaphore = new Semaphore(1);
        public void lock() throws InterruptedException {
            semaphore.acquire();
        }
        public void unlock(){
            semaphore.release();
        }
    }
}
