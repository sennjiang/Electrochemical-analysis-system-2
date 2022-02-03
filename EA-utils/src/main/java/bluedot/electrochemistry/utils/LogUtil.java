package bluedot.electrochemistry.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Senn
 * @create 2022/1/25 19:00
 */
public class LogUtil {
    /**
     * 根据线程名获取 Log 对象
     * @return slf4j.Logger
     */
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * 根据 Class 获取 Log 对象
     * @return slf4j.Logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
