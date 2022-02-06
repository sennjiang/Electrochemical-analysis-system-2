package bluedot.electrochemistry.cache;

import java.util.concurrent.ExecutionException;

/**
 * @author Senn
 * @create 2022/2/4 21:06
 */
public interface Cacheable<K, V> {
    /**
     * 获取一个值
     * @param key key
     * @return T
     */
    V get(K key) throws ExecutionException;

    /**
     * 获取一个值 无则 null
     * @param key key
     * @return T or null
     */
    V getIfPresent(K key);

    /**
     * 要么返回已经缓存的值，要么使用CacheLoader向缓存原子地加载新值；
     * @param t t
     */
    void put(K key , V t);

    /**
     * 返回大小
     * @return long
     */
    long size();

    /**
     * 清除一个值
     * @param key key
     */
    void invalidate(K key);

    /**
     * 清除所有值
     */
    void invalidateAll();

    /**
     * 显示缓存状态
     */
    void stats();
}
