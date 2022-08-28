package bluedot.electrochemistry.cache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Senn
 * @create 2022/3/20 20:48
 */
public class SimpleLock implements Lock {

    private static SimpleSync sync;

    private static class SimpleSync extends AbstractQueuedSynchronizer{
        public void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            }else {
                acquire(1);
            }
        }

        @Override
        protected boolean tryAcquire(int arg) {
            final Thread thread = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if (thread == getExclusiveOwnerThread()){
                int nextState = arg + state;
                setState(nextState);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int c = getState() - arg;
            boolean flag = false;
            if (c == 0) {
                flag = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return flag;
        }
    }

    static {
        sync = new SimpleSync();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
