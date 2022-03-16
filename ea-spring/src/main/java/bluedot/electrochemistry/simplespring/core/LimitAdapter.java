package bluedot.electrochemistry.simplespring.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/3/16 17:04
 */
public class LimitAdapter {

    private static Map<String, LimitDefinition> urlLimit = new HashMap<>();

    public static void setRequestLimiter(String url, int rate, int timeout) {
        LimitDefinition limitDefinition = new LimitDefinition(new Semaphore(rate), timeout);
        urlLimit.put(url, limitDefinition);
    }
    static class LimitDefinition {
        Semaphore semaphore;
        int timeout;
        public LimitDefinition(Semaphore semaphore, int timeout) {
            this.semaphore = semaphore;
            this.timeout = timeout;
        }

        public Semaphore getSemaphore() {
            return semaphore;
        }

        public void setSemaphore(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }

    public static void RateLimiter(String url) throws InterruptedException {
        LimitDefinition limitDefinition = urlLimit.get(url);
        limitDefinition.semaphore.tryAcquire(limitDefinition.timeout, TimeUnit.MILLISECONDS);
    }
}
