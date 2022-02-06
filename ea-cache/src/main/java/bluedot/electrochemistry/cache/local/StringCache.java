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
public class StringCache implements Cacheable<String, String> {

    private LoadingCache<String, String> CACHE;

    private static StringCache accessCache;

    private StringCache() {
    }

    public static StringCache getInstance() {
        if (accessCache == null) throw new RuntimeException("StringCache not init...");
        return accessCache;
    }

    public void init(CacheLoader<String, String> cacheLoader) {
        this.CACHE = CacheBuilder
                .newBuilder()
                .initialCapacity(100)
                .maximumSize(200)
                .recordStats()
                .expireAfterAccess(20, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build(cacheLoader);
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
    public long size() {
        return CACHE.size();
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
