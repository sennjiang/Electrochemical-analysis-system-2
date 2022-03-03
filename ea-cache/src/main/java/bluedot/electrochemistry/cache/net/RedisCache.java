package bluedot.electrochemistry.cache.net;

import bluedot.electrochemistry.cache.RedisCacheable;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Senn
 * @create 2022/2/4 20:35
 */
public class RedisCache implements RedisCacheable {

    private final Jedis jedis;

    public RedisCache(String ip, int port, String password) {
        jedis = new Jedis(ip, port);
        jedis.auth(password);
    }
    public void close() {
        jedis.disconnect();
    }

    @Override
    public void setList(List<?> list) {

    }

    @Override
    public void setMap(Map<?, ?> map) {

    }

    @Override
    public void setSet(Set<?> set) {

    }

    @Override
    public String ping() {
        return jedis.ping();
    }

    @Override
    public void setTimeOut(String key, int time) {

    }
}
