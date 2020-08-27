package juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample2Exception {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(6);
                    //System.out.println("t1 about to finish");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                   cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        TimeUnit.MICROSECONDS.sleep(100);
        TimeUnit.SECONDS.sleep(4);
        System.out.println("number-waiting: " + cyclicBarrier.getNumberWaiting());
        System.out.println("parties: " + cyclicBarrier.getParties());
        System.out.println("isBroken: " + cyclicBarrier.isBroken());
        TimeUnit.SECONDS.sleep(2);
        cyclicBarrier.reset();
        System.out.println("number-waiting: " + cyclicBarrier.getNumberWaiting());
        System.out.println("parties: " + cyclicBarrier.getParties());
        System.out.println("isBroken: " + cyclicBarrier.isBroken());
    }
}
