package bluedot.electrochemistry.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Senn
 * @create 2022/3/3 21:32
 */
public interface RedisCacheable {

    void setList(List<?> list);

    void setMap(Map<?, ?> map);

    void setSet(Set<?> set);

    String ping();

    void setTimeOut(String key, int time);
}
