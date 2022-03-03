package bluedot.electrochemistry.commons.factory;

import bluedot.electrochemistry.cache.entity.FileData;
import bluedot.electrochemistry.cache.local.FileDataCache;
import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.dao.BaseMapper;
import bluedot.electrochemistry.commons.entity.EaFile;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import com.google.common.cache.CacheLoader;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Senn
 * @create 2022/2/5 19:30
 */
public class CacheExecutorFactory implements Lifecycle {

    private static Jedis jedis;

    //获取文件缓存
    public static FileDataCache createFileCache() {
        return FileDataCache.getInstance();
    }

    //获取Redis缓存
    public static Jedis createJedis() {
        return jedis;
    }

    @Override
    public void init() {
        FileDataCache.init(new CacheLoader<String, FileData>() {
            @Override
            public FileData load(String id) throws Exception {
                MapperFactory factory = (MapperFactory) BeanContainer.getInstance().getBean(MapperFactory.class);
                BaseMapper mapper = factory.createMapper();
                EaFile useFile = mapper.getFileById(Long.valueOf(id));
                File file = new File(useFile.getPath());
                FileInputStream reader = new FileInputStream(file);
                //TODO get fileData from file.
                return new FileData();
            }
        });
        jedis = new Jedis("124.222.216.219", 6379);
        jedis.auth("bluedot");
    }

    @Override
    public void destroy() {

    }
}
