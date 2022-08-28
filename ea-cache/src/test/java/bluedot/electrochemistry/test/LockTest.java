package bluedot.electrochemistry.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Senn
 * @create 2022/3/17 19:27
 */
public class LockTest {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    static int i = 0;
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    static void test() throws InterruptedException {
        readLock.lock();
        System.out.println(i);
        synchronized (LockTest.class) {
            Thread.sleep(1000);
            i++;
            System.out.println("writeLock + " + i);
        }
        readLock.unlock();

    }

}
