package bluedot.electrochemistry.simplespring.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Senn
 * @create 2022/1/21 17:31
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String[] value() default "";
}
