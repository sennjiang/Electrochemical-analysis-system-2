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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

        Parameter[] parameterTypes = method.getParameters();

        if (parameterTypes.length == 0) {
            return null;
        }
        int length = parameterTypes.length;
        Object[] params = new Object[length];
        for (int i = 0 ; i <  length ; i ++) {
            Parameter parameterType = parameterTypes[i];
            //@RequestParam 或 基础类型
            if (ConverterUtil.isPrimitive(parameterType.getType()) || parameterType.isAnnotationPresent(RequestParam.class) || parameterType.getType().isArray()) {

                String[] strings =  getRequestParam(parameterType,parameterMap);

                //基本类型;
                if (!parameterType.getType().isArray()) {
                    params[i] = ConverterUtil.convert(parameterType.getType(),strings[0]);
                }else {
                    //TOTO 参数为 基本类型数组 未处理
                    params[i] = strings;
                }

            }else {
                Class<?> type = parameterType.getType();
                Object o = type.newInstance();
                Field[] declaredFields = type.getDeclaredFields();
                if (type.isAnnotationPresent(Param.class)) {
                    for (Field declaredField : declaredFields) {
                        String fieldName = declaredField.getName();
                        String[] strings = parameterMap.get(fieldName);

                        String methodName = "set" + StringUtil.firstCharToUpperCase(fieldName);
                        Method curMethod = type.getMethod(methodName, declaredField.getClass());
                        curMethod.setAccessible(true);
                        Object convert = ConverterUtil.convert(declaredField.getClass(), strings[0]);
                        curMethod.invoke(o, convert);
                    }
                }else {
                    for (Field field : declaredFields) {
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
                            System.out.println(strings);
                            String methodName = "set" + StringUtil.firstCharToUpperCase(fieldName);
                            Method curMethod = parameterType.getType().getMethod(methodName, field.getClass());
                            curMethod.setAccessible(true);
                            Object convert = ConverterUtil.convert(field.getClass(), strings[0]);
                            curMethod.invoke(o,convert);
                        }
                    }

                }
                params[i] = o;
            }
        }
        return params;
    }

    /**
     * 获取基础变量 数组(RequestParam) 或者 加了注解 RequestParam
     * @param parameterType Parameter参数对象
     * @param parameterMap Request parameterMap 请求参数对象
     * @return String[] 请求值
     */
    private String[] getRequestParam(Parameter parameterType, Map<String, String[]>  parameterMap) {
        String paramName = "";
        RequestParam requestParam = parameterType.getAnnotation(RequestParam.class);
        if (requestParam != null) {
            String[] value = requestParam.value();
            if (value.length != 0) {
                paramName = value[0];
            }else {
                paramName = parameterType.getName();
            }
        }else {
            paramName = parameterType.getName();
        }
        return parameterMap.get(paramName);
    }

}
