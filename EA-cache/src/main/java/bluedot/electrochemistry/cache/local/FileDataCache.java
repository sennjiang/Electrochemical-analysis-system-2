package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import bluedot.electrochemistry.commons.entity.FileData;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/4 20:44
 */
public class FileDataCache implements Cacheable<FileData> {

    private CacheLoader<String, FileData> cacheLoader;

    private int initialCapacity;

    private final LoadingCache<String, FileData> CACHE = CacheBuilder
            .newBuilder()
            .initialCapacity(initialCapacity)
            .maximumSize(200)
            .recordStats()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .build(cacheLoader);


    public FileDataCache(int initialCapacity, CacheLoader<String, FileData> cacheLoader) {
        this.initialCapacity = initialCapacity;
        this.cacheLoader = cacheLoader;
    }

    @Override
    public FileData get(String key) throws ExecutionException {
        return CACHE.get(key);
    }

    @Override
    public FileData getIfPresent(String key) {
        return CACHE.getIfPresent(key);
    }

    @Override
    public void put(String key , FileData fileData) {
        CACHE.put(key, fileData);
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
