package bluedot.electrochemistry.test;

import bluedot.electrochemistry.cache.SafeInteger;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Senn
 * @create 2022/3/18 19:29
 */
public class Test {

    public static void main(String[] args) throws NoSuchFieldException {
        SafeInteger integer = new SafeInteger();
        System.out.println(integer.get());
        integer.set(12);
//        System.out.println(integer.get());
//        integer.compareAndSet(12, 15);
//        System.out.println(integer.get());
        new Thread(() -> {
            System.out.println(integer.increaseAndGet());
        }, "t1").start();

        new Thread(() -> {
            System.out.println(integer.increaseAndGet());
        }, "t2").start();

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        readLock.lock();
    }
    private void test1() {
        String b = "12345";
        String a = new String("12345");
        String c = a.intern();
        System.out.println(a == b);
        System.out.println(b == c);
        Object obj = new Object();
        HashMap<String, Object> map = new HashMap();
        map.put("1",1);
        ConcurrentHashMap<String, Object> cmap = new ConcurrentHashMap<>();
        cmap.put("1", "sdfasdfasdf");
        cmap.get("1");
        ReentrantLock lock = new ReentrantLock();
        lock.tryLock();
        lock.lock();
        lock.unlock();
    }
}
