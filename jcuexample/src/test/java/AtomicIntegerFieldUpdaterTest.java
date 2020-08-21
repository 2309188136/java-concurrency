import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"value");
        TestMe testMe = new TestMe();
        for (int i = 0; i< 2; i++){
            new Thread(new Runnable() {
                final int MAX = 20;
                @Override
                public void run() {
                    for (int i=0; i< MAX; i++){
                        int v= updater.getAndIncrement(testMe);
                        System.out.println(Thread.currentThread().getName() + " executed value: " + v);
                    }
                }
            }).start();
        }

    }
    private static class TestMe{
        volatile int value;
    }
}
