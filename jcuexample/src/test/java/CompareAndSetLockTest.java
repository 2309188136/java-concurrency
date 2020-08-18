public class CompareAndSetLockTest {
    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {
        for(int i =0; i< 5; i ++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        doSomething();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (LockAquireFailureException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    private static void doSomething() throws InterruptedException, LockAquireFailureException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " Attempt acquire lock");
            Thread.sleep(10000);
        }
        finally {
            lock.unlock();
        }
    }
}

