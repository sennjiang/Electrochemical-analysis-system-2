package bluedot.electrochemistry.simplemybatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 每个线程获取不同的Logger对象
 * @author xxbb
 */
public class LogUtils {
    /**
     * 根据线程名获取 Log 对象
     * @return slf4j.Logger
     */
    public static Logger getLogger() {
        return LoggerFactory.getLogger(Thread.currentThread().getName());
    }

    /**
     * 根据 Class 获取 Log 对象
     * @return slf4j.Logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
