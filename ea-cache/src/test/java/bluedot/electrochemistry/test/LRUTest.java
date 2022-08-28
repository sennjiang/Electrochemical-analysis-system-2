package bluedot.electrochemistry.test;

import bluedot.electrochemistry.cache.LocalCacheBuilder;
import bluedot.electrochemistry.cache.bloom.DefaultBloomFilter;
import bluedot.electrochemistry.cache.local.CacheIterator;
import bluedot.electrochemistry.cache.local.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/3/16 12:16
 */
public class LRUTest {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        LRUCache<String, Object> cache = new LocalCacheBuilder<String, Object>()
                .setCapacity(100)
                .setFactor(0.25F)
                .setInterval(3000)
                .setFilter(new DefaultBloomFilter<>())
                .build();

        System.out.println("--------------插入与搜索-------------");
        cache.put("1","你好");
        System.out.println(cache.get("1"));
        System.out.println("--------------热链表遍历-------------");
        CacheIterator<String, Object> hotEntry1 = cache.getHotEntry();
        while (hotEntry1.hasNext()) {
            LRUCache.Node<String, Object> next = hotEntry1.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }

        System.out.println("--------------冷链表遍历-------------");
        CacheIterator<String, Object> coldEntry = cache.getColdEntry();
        while (coldEntry.hasNext()) {
            LRUCache.Node<String, Object> next = coldEntry.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
        Thread.sleep(4000);
        System.out.println("-------------热点查询--------------");
        Object o = cache.get("1");
        System.out.println(o);

        System.out.println("--------------热链表遍历-------------");
        CacheIterator<String, Object> hotEntry = cache.getHotEntry();
        while (hotEntry.hasNext()) {
            LRUCache.Node<String, Object> next = hotEntry.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
        System.out.println("-------------冷链表遍历--------------");
        CacheIterator<String, Object> coldEntry1 = cache.getColdEntry();
        while (coldEntry1.hasNext()) {
            LRUCache.Node<String, Object> next = coldEntry1.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }
    public static  int localTime() {
        long time = System.currentTimeMillis();
        return (int) (time % 3000);
    }
}
