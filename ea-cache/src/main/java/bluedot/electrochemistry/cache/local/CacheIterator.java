package bluedot.electrochemistry.cache.local;

import java.util.Iterator;

/**
 * @author Senn
 * @create 2022/3/16 15:13
 */
public class CacheIterator<K, V> implements Iterator<LRUCache.Node<K, V>> {

    LRUCache.Node<K, V> head;

    int capacity;

    public CacheIterator(LRUCache.Node<K, V> head) {
        this.head = head;
    }
    public void setCapacity(int size) {
        this.capacity = size;
    }

    @Override
    public boolean hasNext() {
        return head.next != null && capacity > 0;
    }

    @Override
    public LRUCache.Node<K, V> next() {
        LRUCache.Node<K, V> cur = head.next;
        head = head.next;
        capacity--;
        return cur;
    }
}
