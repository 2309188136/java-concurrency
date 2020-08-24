import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class UnsafeTest2 {
    public static void main(String[] args) {
        System.out.println(sizeof(new Simple()));
    }
    private static long sizeof(Object obj){
        Unsafe unsafe = getUnsafe();
        Set<Field> fields = new HashSet<>();
        Class c = obj.getClass();
        while(c != Object.class){
            Field[] declaredFields = c.getDeclaredFields();
            for(Field f: declaredFields){
                if((f.getModifiers() & Modifier.STATIC)==0){
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }
        long maxoffset = 0;
        for (Field f: fields){
            long offSet = unsafe.objectFieldOffset(f);
            if(offSet > maxoffset){
                maxoffset = offSet;
            }
        }

        return ((maxoffset / 8) + 1) * 8;
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
    static class Simple{
        private long l = 0;
        public Simple() {
            this.l =1;
            System.out.println("==================================");
        }
        public long get(){
            return this.l;
        }
    }
}
