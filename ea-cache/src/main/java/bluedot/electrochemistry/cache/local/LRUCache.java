package bluedot.electrochemistry.cache.local;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 基于 双向链表 + HashMap 的 LRU 算法实现，对算法的解释如下：
 * 访问某个节点时，将其从原来的位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的
 * 就是最近最久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。
 * 为了使删除操作时间复杂度为 O(1)，就不能采用遍历的方式找到某个节点。HashMap 存储着 Key
 * 到节点的映射，通过 Key 就能以 O(1) 的时间得到节点，然后再以 O(1) 的时间将其从双向队列中删除。
 *
 * 读写锁
 *
 * @author Senn
 * @create 2022/3/3 20:39
 */
public class LRUCache<K, V> {
    /**
     * 节点
     */
    class Node {
        K key;
        V value;
        Node pre;
        Node next;
        public Node(){}
        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private final Map<K, Node> cache = new HashMap<>();

    private int size;
    private final int capacity;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }
    public V get(K key) {
        readLock.lock();
        try {
            Node node = cache.get(key);
            if (node == null) {
                return null;
            }
            moveToHead(node);
            return node.value;
        }finally {
            readLock.unlock();
        }
    }


    /**
     * 添加缓存
     * @param key key
     * @param value value
     */
    public void put(K key, V value) {
        writeLock.lock();
        try {
            Node node = cache.get(key);
            if (node == null) {
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    Node tail = removeTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 删除尾节点 （tail 前一节点）
     * @return  删除的节点
     */
    private Node removeTail() {
        Node temp = tail.pre;
        removeNode(temp);
        return temp;
    }

    /**
     * 移动到头结点
     * @param node 结点
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 删除节点
     * @param node 节点
     */
    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    /**
     * 将节点添加 头
     * @param node 节点
     */
    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
}
