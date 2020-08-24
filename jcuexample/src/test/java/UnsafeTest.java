import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UnsafeTest {


    interface Counter {
        void increment();
        long getCounger();
    }

    private static class CasCounter implements Counter{
        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        CasCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }
        @Override
        public void increment() {
            long current = counter;
            while(!unsafe.compareAndSwapLong(this, offset, current, current+1)){
                current= counter;
            }
        }

        @Override
        public long getCounger() {
            return counter;
        }
        private static Unsafe getUnsafe(){
            try{
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                return (Unsafe)f.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static void main(String[] args) throws NoSuchFieldException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for (int i =0; i< 1000; i++){
            service.submit(new CounterRunnable(counter, 10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounger());
        System.out.println("Time passed in ms: " + (end-start));
    }


}
 class CounterRunnable implements Runnable{
    private final UnsafeTest.Counter counter;
    private final int num;

    CounterRunnable(UnsafeTest.Counter counter, int num) {
        this.counter = counter;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i =0; i< num; i++){
            counter.increment();
        }
    }
}
