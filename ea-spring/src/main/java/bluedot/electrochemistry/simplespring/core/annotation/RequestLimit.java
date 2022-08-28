package bluedot.electrochemistry.simplespring.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Senn
 * @create 2022/3/16 17:01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {
    /**
     * 超时时间 单位毫秒值
     * @return 超时时间
     */
    int timeout();

    /**
     * 限流大小，单位请求。
     * @return 限流大小
     */
    int rate();
}
