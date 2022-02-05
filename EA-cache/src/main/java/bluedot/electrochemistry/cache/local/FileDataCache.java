package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.Cacheable;
import bluedot.electrochemistry.cache.entity.FileData;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/4 20:44
 */
public class FileDataCache implements Cacheable<String, FileData> {

    private static volatile FileDataCache fileDataCache;

    private static LoadingCache<String, FileData> CACHE;

    public static FileDataCache getInstance() {
        if (fileDataCache == null) {
           throw new RuntimeException("file cache not init...");
        }
        return fileDataCache;
    }

    public static void init(CacheLoader<String, FileData> cacheLoader) {
        CACHE = CacheBuilder
                .newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .recordStats()
                .expireAfterAccess(20, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build(cacheLoader);
    }


    private FileDataCache() {
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
