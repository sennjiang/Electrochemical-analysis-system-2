package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;

/**
 * @author Senn
 * @create 2022/2/4 21:29
 */
public class MetaCache implements Cacheable<String> {

    private CacheLoader<String, String> cacheLoader;

    private int initialCapacity;

    private RemovalListener<String, String> listener;

    private final LoadingCache<String, String> CACHE = CacheBuilder
            .newBuilder()
            .initialCapacity(initialCapacity)
            .maximumSize(200)
            .recordStats()
            .removalListener(listener)
            .build(cacheLoader);

    public MetaCache(int initialCapacity, CacheLoader<String, String> cacheLoader, RemovalListener<String, String> listener) {
        this.initialCapacity = initialCapacity;
        this.cacheLoader = cacheLoader;
        this.listener = listener;
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
    public void put(String key, String s) {
        CACHE.put(key,s);
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
