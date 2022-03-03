package bluedot.electrochemistry.cache.net;

import redis.clients.jedis.Jedis;

/**
 * @author Senn
 * @create 2022/2/4 20:35
 */
public class RedisCache {

    private final Jedis jedis;

    public RedisCache(String ip, int port, String password) {
        jedis = new Jedis(ip, port);
        jedis.auth(password);

    }
    public void close() {
        jedis.disconnect();
    }
}
