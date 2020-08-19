import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
//        AtomicReference<Simple> atomicSimple= new AtomicReference<>(new Simple("James",29));
        Simple simple = new Simple("James", 29);
        //AtomicReference --> creates possible ABA problem
        AtomicReference<Simple> atomicSimple= new AtomicReference<>(simple);
        System.out.println(atomicSimple.get());
        //expect false
        boolean falseResult = atomicSimple.compareAndSet(new Simple("Henry", 22), new Simple("Andy", 23));
        System.out.println(falseResult);
        boolean falseResult1 = atomicSimple.compareAndSet(new Simple("James",29), new Simple("Andy", 23));
        System.out.println(falseResult1);
        boolean result = atomicSimple.compareAndSet(simple, new Simple("Andy", 23));
        System.out.println(result);
    }
    private static class Simple{

        private String name;
        private int age;

        private Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
