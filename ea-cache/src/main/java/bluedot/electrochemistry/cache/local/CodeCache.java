package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/7 19:42
 */
public class CodeCache implements Cacheable<String, String> {

    private static LoadingCache<String, String> CACHE;

    private static CodeCache codeCache;

    private CodeCache() {
    }

    public static CodeCache getInstance() {
        if (codeCache == null) throw new RuntimeException("codeCache not init...");
        return codeCache;
    }

    public static void init(CacheLoader<String, String> cacheLoader) {
        CACHE = CacheBuilder
                .newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .recordStats()
                .expireAfterAccess(3, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build(cacheLoader);
        codeCache = new CodeCache();
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
