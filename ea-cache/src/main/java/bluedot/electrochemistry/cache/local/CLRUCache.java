package bluedot.electrochemistry.cache.local;

import bluedot.electrochemistry.cache.LocalCacheBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * v1.0
 * 基于 双向链表 + HashMap 的 LRU 算法实现，对算法的解释如下：
 * 访问某个节点时，将其从原来的位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的
 * 就是最近最久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。
 * 为了使删除操作时间复杂度为 O(1)，就不能采用遍历的方式找到某个节点。HashMap 存储着 Key
 * 到节点的映射，通过 Key 就能以 O(1) 的时间得到节点，然后再以 O(1) 的时间将其从双向队列中删除。
 *
 * v1.1
 * 读写锁
 *
 * v2.0
 * 升级 使用冷热链表
 *
 * @author Senn
 * @create 2022/3/17 19:47
 */
public class CLRUCache<K, V> extends LRUCache<K, V>{

    /**
     * 使用建造者模式创建
     *
     * @param builder 建造者
     */
    public CLRUCache(LocalCacheBuilder<K, V> builder) {
        super(builder);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        //判断是否为 冷节点
        if (node instanceof ColdNode) {
            ColdNode<K, V> coldNode = (ColdNode<K, V>) node;
            if (needToHot(coldNode)) {
                moveToHotHead(coldNode);
                int size = hotSize.incrementAndGet();
                if (size >= hotThreshold) {
                    moveFromHotToColdHead(coldHead.pre);
                }
            }else {
                moveToColdHead(coldNode);
            }
        }else {
            moveToHotHead(node);
        }
        return node.value;
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
    }
}
