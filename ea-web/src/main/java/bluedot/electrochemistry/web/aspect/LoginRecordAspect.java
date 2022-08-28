package bluedot.electrochemistry.web.aspect;

import bluedot.electrochemistry.simplespring.aop.annotation.Aspect;
import bluedot.electrochemistry.simplespring.aop.annotation.Order;
import bluedot.electrochemistry.simplespring.aop.aspect.DefaultAspect;
import bluedot.electrochemistry.utils.LogUtil;
import org.slf4j.Logger;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author xxbb
 */
@Aspect(pointcut = "execution(* com.bluedot.pig.controller.LoginController.*(..))")
@Order(1)
public class LoginRecordAspect extends DefaultAspect {

    private static final Logger LOGGER =  LogUtil.getLogger(LoginRecordAspect.class);
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        LOGGER.debug("登录日志日志类的前置通知");
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        LOGGER.debug("登录日志日志类的后置通知");
        insertLoginLog(args,null);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable throwable) throws Throwable {
        LOGGER.debug("登录日志日志类的异常通知");
        insertLoginLog(args,throwable);
    }

    /**
     * 插入登录日志的具体操作
     * @param throwable 异常
     */
    private void insertLoginLog(Object[] args,Throwable throwable) throws IOException {
       //TODO 登录日志
    }
}
