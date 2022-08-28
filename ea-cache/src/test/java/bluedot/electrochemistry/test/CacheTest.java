package bluedot.electrochemistry.test;

import bluedot.electrochemistry.utils.LogUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.slf4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/13 20:01
 */
public class CacheTest {

    private static final Logger LOGGER = LogUtil.getLogger(CacheTest.class);

    private static final Cache<String, String> CALLABLE_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS).maximumSize(1000).recordStats()
            .removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(RemovalNotification<Object, Object> notification) {
                    LOGGER.info("Remove a map entry which key is {},value is {},cause is {}.", notification.getKey(),
                            notification.getValue(), notification.getCause().name());
                }
            }).build();


    public static void main(String[] args) throws ExecutionException {
        String s = CALLABLE_CACHE.get("1", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "2";
            }
        });
        //2
        System.out.println(s);
        //2
        System.out.println(CALLABLE_CACHE.getIfPresent("1"));
    }
}
