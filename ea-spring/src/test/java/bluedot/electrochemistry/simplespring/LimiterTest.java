package bluedot.electrochemistry.simplespring;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Senn
 * @create 2022/3/6 20:36
 */
public class LimiterTest {

        RateLimiter rateLimiter = RateLimiter.create(10);

        public void doPay(String name) {
            if (rateLimiter.tryAcquire()) {
                System.out.println(name + "支付成功");
            } else {
                System.out.println(name + "支付失败请求繁忙，请稍后重试");
            }
        }

        public static void main(String[] args) throws InterruptedException {
            final Semaphore semaphore = new Semaphore(1);

            new Thread(() -> {
                try {
                    semaphore.tryAcquire(3, TimeUnit.SECONDS);
                    System.out.println(1);
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t1").start();

            new Thread(() -> {
                try {
                    if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
                        System.out.println(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t1").start();


        }
        private void testLimit() {
            LimiterTest app = new LimiterTest();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Random random = new Random(10);
            for (int i = 0; i < 20; i++) {
                final int fi = i;
                Thread t = new Thread(() -> {
                    try {
                        countDownLatch.await();
                        Thread.sleep(random.nextInt(1000));
                        app.doPay("Thread-" + fi + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                t.start();
            }

            countDownLatch.countDown();

        }
    }
