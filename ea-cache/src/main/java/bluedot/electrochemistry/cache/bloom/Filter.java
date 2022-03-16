package bluedot.electrochemistry.cache.bloom;

import bluedot.electrochemistry.cache.BloomFilter;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Senn
 * @create 2022/3/16 16:04
 */
public class Filter implements BloomFilter<String> {

    /**
     * 布隆过滤器最大容量
     */
    private int size;

    /**
     * 存储散列值列表
     */
    private BitSet bitSet;


    /**
     * 当前使用hash位数，位数越多误判概率越低，内存占用就会越多
     */
    private int[] seeds;

    public Filter(){}

    public Filter(int capacity) {
        this(SeedsEnum.MIDDLE, capacity);
    }

    public Filter(SeedsEnum seedsEnum, int capacity) {
        this.size = seedsEnum.seeds.length * capacity;
        this.seeds = seedsEnum.seeds;
        this.bitSet = new BitSet(this.size);
    }

    /**
     * 新增元素
     * @param element element
     */
    @Override
    public void add(String element){

        for (int seed : seeds) {
            //计算hash值
            int hashCode = hash(element, seed);
            setBitSet(hashCode);
        }
    }

    @Override
    public void remove(String s) {

    }

    /**
     * 判断元素是否已存在
     * @param element  element
     * @return boolean
     */
    @Override
    public boolean contain(String element) {
        boolean exit = true;
        for (int seed : seeds) {
            //计算hash值
            int hashCode = hash(element, seed);
            if (!bitSet.get(hashCode)) {
                exit = false;
                break;
            }
        }
        return exit;
    }

    /**
     * 如果元素存在则返回true，不存在则增加并返回false
     * @param element element
     * @return boolean
     */
    public boolean addIfNoExit(String element) {

        boolean exit = true;
        int[] hashCodes = new int[seeds.length];

        for (int i = 0; i < seeds.length; i++) {
            //计算hash值
            int hashCode = hash(element, seeds[i]);
            hashCodes[i] = hashCode;

            //重点说明下这段代码，只要有一次hash不存在，就说明这个元素不存在。

            //遍历整个hash判断，如果存在就去bitSet中判断当前hash是否为true，如果为fale，说明不存在，
            //那么当前hash值及之前都要存入bitSet中，if(exit)会自动将剩余hash值放入bitSet中
            //如此便可完成存在返回true，不存在就放入bitSet中，返回false
            if (exit) {
                if (!bitSet.get(hashCode)) {
                    exit = false;

                    //补充原有
                    for (int j = 0; j < i + 1; j++) {
                        setBitSet(hashCodes[j]);
                    }
                }
            } else {
                setBitSet(hashCode);
            }

        }
        return exit;
    }

    private void setBitSet(int hashCode) {
        bitSet.set(hashCode, true);
    }

    private int hash(String element, int seed) {
        char[] chars = element.toCharArray();
        int hashCode = 0;

        for (int i = 0; i < chars.length; i++) {
            hashCode = i * hashCode + chars[i];
        }

        hashCode = hashCode * seed % size;
        // 防止溢出变成负数
        return Math.abs(hashCode);
    }

    public enum SeedsEnum {
        /**
         * 每个字符串分配4个位
         */
        VERY_SMALL(new int[]{2, 3, 5, 7}),
        /**
         * 每个字符串分配8个位
         */
        SMALL(new int[]{2, 3, 5, 7, 11, 13, 17, 19}),
        /**
         * 每个字符串分配16个位
         */
        MIDDLE(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53}),
        /**
         * 每个字符串分配32个位
         */
        HIGH(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103, 107, 109, 113, 127, 131});

        private int[] seeds;

        private SeedsEnum(int[] seeds) {
            this.seeds = seeds;
        }

        public int[] getSeeds() {
            return seeds;
        }

        public void setSeeds(int[] seeds) {
            this.seeds = seeds;
        }
    }


}

