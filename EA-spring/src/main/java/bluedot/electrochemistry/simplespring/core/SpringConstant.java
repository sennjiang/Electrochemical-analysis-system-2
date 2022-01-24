package bluedot.electrochemistry.simplespring.core;

import bluedot.electrochemistry.simplespring.aop.annotation.Aspect;
import bluedot.electrochemistry.simplespring.core.annotation.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author Senn
 * @create 2022/1/24 20:21
 */
public class SpringConstant {
    /**
     * 加载bean的注解列表
     */
    public static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(
            Component.class,
            Controller.class,
            Service.class,
            Repository.class,
            Aspect.class,
            Configuration.class,
            Filter.class,
            BeforeFilter.class,
            AfterFilter.class);
}
