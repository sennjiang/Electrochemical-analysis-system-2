package bluedot.electrochemistry.cache;

class SimpleLockTest {

    private static SimpleLock lock = new SimpleLock();

    static int resource = 0;

    public static void doResource() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);
        resource--;
        System.out.println(Thread.currentThread().getName() +" --> "+ resource);
        lock.unlock();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                SimpleLockTest.doResource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }, "t1").start();

        new Thread(() -> {
            try {
                SimpleLockTest.doResource();
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }, "t2").start();
    }
}