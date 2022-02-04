package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/4 20:40
 */
public class AccessCache implements Cacheable<String> {

    private CacheLoader<String, String> cacheLoader;

    private int initialCapacity;

    private final LoadingCache<String, String> CACHE = CacheBuilder
            .newBuilder()
            .initialCapacity(initialCapacity)
            .maximumSize(200)
            .recordStats()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .build(cacheLoader);

    public AccessCache(int initialCapacity, CacheLoader<String, String> cacheLoader) {
        this.initialCapacity = initialCapacity;
        this.cacheLoader = cacheLoader;
    }


    @Override
    public String get(String key) throws ExecutionException {
        return CACHE.get(key);
    }

    @Override
    public String getIfPresent(String key) {
        return CACHE.getIfPresent(key);
    }

    @Override
    public void put(String key , String object) {
        CACHE.put(key, object);
    }

    @Override
    public void invalidate(String key) {
        CACHE.invalidate(key);
    }

    @Override
    public void invalidateAll() {
        CACHE.invalidateAll();
    }

    @Override
    public void stats() {
        CACHE.stats();
    }
}
