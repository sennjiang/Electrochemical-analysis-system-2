package bluedot.electrochemistry.cache;

import java.util.concurrent.ExecutionException;

/**
 * @author Senn
 * @create 2022/2/4 21:06
 */
public interface Cacheable<T> {
    /**
     * 获取一个值
     * @param key key
     * @return T
     */
    T get(String key) throws ExecutionException;

    /**
     * 获取一个值 无则 null
     * @param key
     * @return T or null
     */
    T getIfPresent(String key);

    /**
     * 要么返回已经缓存的值，要么使用CacheLoader向缓存原子地加载新值；
     * @param t t
     */
    void put(String key , T t);

    /**
     * 清除一个值
     * @param key key
     */
    void invalidate(String key);

    /**
     * 清除所有值
     */
    void invalidateAll();

    /**
     * 显示缓存状态
     */
    void stats();
}
