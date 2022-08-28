package bluedot.electrochemistry.commons.filter;

import bluedot.electrochemistry.cache.BloomFilter;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Senn
 * @create 2022/3/29 19:22
 */
public class SetFilter implements BloomFilter<Integer> {

    /**
     * 位数组
     */
    private BitSet bitSet;

    /**
     * 大小
     */
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * 容量
     */
    private int capacity;

    /**
     * 阈值
     */
    private volatile int threshold;

    /**
     * 平衡因子
     */
    private final float loadFactor;

    /**
     * 默认平衡因子
     */
    protected static final float DEFAULT_LOAD_FACTOR =  0.75f;

    /**
     * 默认最大容量
     */
    protected static final int MAX_SET_FILTER_VALUE = 1 << 30;

    public SetFilter(int capacity) {
        this(capacity,DEFAULT_LOAD_FACTOR);
    }

    public SetFilter(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        threshold = (int) (loadFactor * capacity);
    }

    @Override
    public void add(Integer integer) {
        int n = size.incrementAndGet();
        if (n > threshold) {
            resize();
        }
        bitSet.set(integer);
    }

    /**
     * 扩容
     * 2倍
     * 加锁
     */
    private void resize() {
        int oldthr = this.threshold;
        synchronized (SetFilter.class) {
            if (oldthr == this.threshold) {
                int newCap;
                if (capacity >= MAX_SET_FILTER_VALUE / 2) {
                    newCap = MAX_SET_FILTER_VALUE;
                } else {
                    newCap =  capacity << 1;
                }
                this.threshold = (int) (newCap * loadFactor);
                BitSet set = new BitSet(newCap);
                set.or(bitSet);
                this.bitSet = set;
                this.capacity = newCap;
            }
        }
    }

    @Override
    public void remove(Integer index) {
        bitSet.clear(index);
    }

    @Override
    public boolean contain(Integer index) {
        return bitSet.get(index);
    }
}
