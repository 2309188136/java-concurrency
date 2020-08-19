import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    private static AtomicStampedReference<Integer>  atomicStampedReference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   TimeUnit.SECONDS.sleep(1);
                   boolean success = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
                   System.out.println(success);
                   success=atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
                   System.out.println(success);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int stamp = atomicStampedReference.getStamp();
                    System.out.println("Before sleep satmp=: " + stamp);
                    TimeUnit.SECONDS.sleep(2);
                    boolean success= atomicStampedReference.compareAndSet(100, 101, stamp, stamp+1);
                    System.out.println(success);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
