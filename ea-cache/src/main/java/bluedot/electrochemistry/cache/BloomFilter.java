package bluedot.electrochemistry.cache;

/**
 * @author Senn
 * @create 2022/3/7 12:47
 */
public interface BloomFilter<T> {

    /**
     * 添加
     * @param t T
     */
    void add(T t);

    /**
     * 判断
     * @param t T
     * @return boolean
     */
    public boolean contains(T t);
}
