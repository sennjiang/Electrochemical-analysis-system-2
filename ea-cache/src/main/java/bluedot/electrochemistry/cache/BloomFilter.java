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
     * 删除
     * @param t 元素
     */
    void remove(T t);

    /**
     * 判断
     * @param t T
     * @return boolean
     */
    boolean contain(T t);
}
