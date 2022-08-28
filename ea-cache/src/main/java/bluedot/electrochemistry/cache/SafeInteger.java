package bluedot.electrochemistry.cache;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * @author Senn
 * @create 2022/3/20 20:25
 */
public class SafeInteger {

    private static Unsafe U;

    private static long valueOffset;

    private int value;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            U = (Unsafe) theUnsafe.get(null);
            valueOffset = U.objectFieldOffset(SafeInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public SafeInteger() {
    }

    public SafeInteger(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public void set(int num) {
        value = num;
    }

    public final boolean compareAndSet(int expect, int update) {
        return U.compareAndSwapInt(this, valueOffset, expect, update);
    }

    public int increaseAndGet(){
        return U.getAndAddInt(this, valueOffset, 1);
    }

}
