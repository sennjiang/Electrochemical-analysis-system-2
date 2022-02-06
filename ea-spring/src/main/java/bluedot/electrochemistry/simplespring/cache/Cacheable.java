package bluedot.electrochemistry.simplespring.cache;

/**
 * spring请求缓存外接类 使用其他缓存时许实现此接口
 * @author Senn
 * @create 2022/1/21 18:15
 */
public interface Cacheable {

    <T> void put(T t);

    <T> Object get(T t);
}
