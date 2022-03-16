package bluedot.electrochemistry.cache;

import bluedot.electrochemistry.cache.local.LRUCache;

/**
 * @author Senn
 * @create 2022/3/16 14:33
 */
public class LocalCacheBuilder<K, V> {
    /**
     * 冷热链表节点比值 100 = 热 75 + 冷 25;
     */
    float factor = 0.25f;

    /**
     * 默认 冷数据升级 时间间隔
     */
    int interval = 3000;

    /**
     * 默认缓存大小
     */
    int capacity = 100;

    public LocalCacheBuilder<K, V> setFactor(float factor){
        this.factor = factor;
        return this;
    }

    public LocalCacheBuilder<K, V> setInterval(int interval){
        this.interval = interval;
        return this;
    }

    public LocalCacheBuilder<K, V> setCapacity(int capacity){
        this.capacity = capacity;
        return this;
    }
    public LRUCache<K, V> build(){
        return new LRUCache<>(this);
    }

    public float getFactor() {
        return factor;
    }

    public int getInterval() {
        return interval;
    }

    public int getCapacity() {
        return capacity;
    }
    public int getColdThreshold(){
        return (int) (this.capacity * this.factor);
    }
    public int getHotThreshold(){
        return (int) (this.capacity * (1 - this.factor));
    }
}
