package bluedot.electrochemistry.cache.bloom;

import bluedot.electrochemistry.cache.BloomFilter;

import java.util.BitSet;

/**
 * 布隆过滤器
 * @author Senn
 * @create 2022/3/3 21:27
 */
public class DefaultBloomFilter implements BloomFilter<Object> {
    // 位数组大小
    private static final int DEFAULT_SIZE = 2 << 24;
    // 位数组
    private final BitSet bitSet = new BitSet(DEFAULT_SIZE);
    // 传入不同的seed创建 6 个不同的哈希函数
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    private final SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 静态内部类,用于hash操作
     */
    public static class SimpleHash {
        private final int cap;
        private final int seed;

        public SimpleHash(int cap, int seed) {
            super();
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    public DefaultBloomFilter() {
        super();
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    @Override
    public void add(Object obj) {
        for (SimpleHash sf : func) {
            bitSet.set(sf.hash(obj), true);
        }
    }

    @Override
    public boolean contains(Object obj) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bitSet.get(f.hash(obj));
        }
        return ret;
    }
}
