package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import bluedot.electrochemistry.cache.entity.FileData;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/4 21:29
 */
public class StringArrayCache implements Cacheable<String, String[]> {

    private static StringArrayCache stringArrayCache;

    private LoadingCache<String, String[]> CACHE;

    private StringArrayCache() {
    }

    public static StringArrayCache getInstance() {
        if (stringArrayCache == null) {
            throw new RuntimeException("file cache not init...");
        }
        return stringArrayCache;
    }

    public void init(CacheLoader<String, String[]> cacheLoader) {
        this.CACHE = CacheBuilder
                .newBuilder()
                .initialCapacity(100)
                .maximumSize(200)
                .recordStats()
                .expireAfterAccess(20, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build(cacheLoader);
        stringArrayCache = new StringArrayCache();
    }

    @Override
    public String[] get(String key) throws ExecutionException {
        return CACHE.get(key);
    }

    @Override
    public String[] getIfPresent(String key) {
        return CACHE.getIfPresent(key);
    }

    @Override
    public void put(String key, String[] s) {
        CACHE.put(key,s);
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
