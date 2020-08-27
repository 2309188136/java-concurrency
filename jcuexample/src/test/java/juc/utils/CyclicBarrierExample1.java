package juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, ()->{
            System.out.println("all work finished");
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(15);
                    System.out.println("T1 finished.");
                    cyclicBarrier.await();
                    System.out.println("T1 - other thread finished too.");
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
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("T2 finished.");
                    cyclicBarrier.await();
                    System.out.println("T2 -The other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
//        cyclicBarrier.await();
//        System.out.println("main thread- all work finished");

        while(true){
            System.out.println("nubmer-waiting: "+cyclicBarrier.getNumberWaiting());
            System.out.println("parties:" + cyclicBarrier.getParties());
            System.out.println("isBorken: " + cyclicBarrier.isBroken());
            TimeUnit.MILLISECONDS.sleep(500);
        }

    }
}
