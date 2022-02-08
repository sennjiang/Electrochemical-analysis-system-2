package bluedot.electrochemistry.utils;

import oracle.jrockit.jfr.VMJFR;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Senn
 * @create 2022/2/8 19:27
 */
public class ConfigUtil {

    private static final Logger LOGGER = LogUtil.getLogger(ConfigUtil.class);

    /**
     * 加载配置文件
     *
     * @param contextConfigLocation properties配置文件
     */
    public static Properties doLoadConfig(String contextConfigLocation) {
        Properties properties = new Properties();
        //直接通过类路径找到框架主配置文件的路径
        //并将配置文件内容读取到properties对象中
        LOGGER.info("Loading configLocation--->path:{} ", contextConfigLocation);
        InputStream is = null;
        try {
            is = ConfigUtil.class.getClassLoader().getResourceAsStream(contextConfigLocation);
            properties.load(is);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}
