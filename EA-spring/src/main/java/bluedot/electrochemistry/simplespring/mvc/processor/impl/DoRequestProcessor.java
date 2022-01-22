package bluedot.electrochemistry.simplespring.mvc.processor.impl;

import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.annotation.Param;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.processor.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.render.impl.JsonResultRender;
import bluedot.electrochemistry.simplespring.util.ConverterUtil;
import bluedot.electrochemistry.simplespring.util.LogUtil;
import bluedot.electrochemistry.simplespring.util.StringUtil;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/1/22 17:02
 */
public class DoRequestProcessor implements RequestProcessor {

    private final BeanContainer beanContainer = BeanContainer.getInstance();

    private final Logger logger = LogUtil.getLogger();

    private static final RequestURLAdapter urlAdapter = (RequestURLAdapter) BeanContainer.getInstance().getBean(RequestURLAdapter.class);

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        String requestPath = requestProcessorChain.getRequestPath();
        logger.info("request path --> {}",requestPath);
        Method method = urlAdapter.getUrl(requestPath);
        Class<?> aClass = urlAdapter.getClass(requestPath);
        if (method == null) throw new RuntimeException("No such request path !!");
        //获取方法参数
        Object[] params = doMethodParam(method,requestProcessorChain.getReq());
        //获取执行对象
        Object o = beanContainer.getBean(aClass);
        //最终执行
        Object invoke = method.invoke(o, params);
        requestProcessorChain.setResultRender(new JsonResultRender(invoke));
        return false;
    }

    private Object[] doMethodParam(Method method, HttpServletRequest req) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Map<String, String[]> parameterMap = req.getParameterMap();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0) {
            return null;
        }
        int length = parameterTypes.length;
        Object[] params = new Object[length];
        for (int i = 0 ; i <  length ; i ++) {
            Class<?> parameterType = parameterTypes[i];
            //@RequestParam 基础类型
            if (parameterType.isAnnotationPresent(RequestParam.class)) {
                RequestParam annotation = parameterType.getAnnotation(RequestParam.class);
                String[] value = annotation.value();
                String[] strings = parameterMap.get(value[0]);
                //string类型;
                if (!parameterType.isArray()) {
                    params[i] = ConverterUtil.convert(parameterType,strings[0]);
                }else {
                    params[i] = strings;
                }
            }else {
                //引用类型
                Field[] fields = parameterType.getFields();
                Object o = parameterType.newInstance();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Param.class)) {
                        String fieldName;
                        Param annotation = field.getAnnotation(Param.class);
                        String[] value = annotation.value();
                        if (value.length == 0) {
                            fieldName = field.getName();
                        }else {
                            fieldName = value[0];
                        }
                        String[] strings = parameterMap.get(fieldName);
                        String methodName = "set" + StringUtil.firstCharToUpperCase(fieldName);
                        Method curMethod = parameterType.getMethod(methodName, field.getClass());
                        curMethod.setAccessible(true);
                        Object convert = ConverterUtil.convert(field.getClass(), strings[0]);
                        curMethod.invoke(o,convert);
                    }
                }
                params[i] = o;
            }
        }
        return params;
    }

}
