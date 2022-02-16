package bluedot.electrochemistry.simplespring.mvc.processor.impl;

import bluedot.electrochemistry.utils.ConverterUtil;
import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.utils.StringUtil;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.annotation.Param;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.filter.FilterAdapter;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.simplespring.mvc.processor.render.impl.JsonResultRender;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
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

    public static final String EMPTY_STRING = "";

    private final BeanContainer beanContainer = BeanContainer.getInstance();

    private static final Logger LOGGER = LogUtil.getLogger("spring.mvc.processor");

    private static final RequestURLAdapter urlAdapter = (RequestURLAdapter) BeanContainer.getInstance().getBean(RequestURLAdapter.class);


    /**
     * 后置过滤器
     */
    private FilterAdapter filterAdapter;

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        String requestPath = requestProcessorChain.getRequestPath();
        LOGGER.info("request path --> {}", requestPath);
        if (filterAdapter.needDoBefore()) {
            filterAdapter.doBeforeFilter(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
        }
        Method method = null;
        if (urlAdapter.isWhiteUrl(requestPath)) {
            method = urlAdapter.getWhiteUrl(requestPath);
        } else {
            method = urlAdapter.getUrl(requestPath);
        }
        if (method == null) throw new RuntimeException("No such request method !!");
        Class<?> aClass = urlAdapter.getClass(requestPath);
        //获取方法参数
        Object[] params = doMethodParam(method, requestProcessorChain, requestProcessorChain.getRequestParams());
        //获取执行对象
        Object o = beanContainer.getBean(aClass);
        //最终执行
        Object returnValue = method.invoke(o, params);
        requestProcessorChain.setResultRender(new JsonResultRender(returnValue));

        if (filterAdapter.needDoAfter()) {
            filterAdapter.doAfterFilter(requestProcessorChain.getRequest(), requestProcessorChain.getResponse(), returnValue);
        }

        return false;
    }

    private Object[] doMethodParam(Method method, RequestProcessorChain chain, Map<String, String[]> parameterMap) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Parameter[] parameterTypes = method.getParameters();

        if (parameterTypes.length == 0) {
            return null;
        }
        int length = parameterTypes.length;
        Object[] params = new Object[length];
        for (int i = 0; i < length; i++) {
            Parameter parameterType = parameterTypes[i];
            //文件类型
            if (parameterType.getType() == MultipartFile[].class) {
                params[i] = chain.getRequestFiles();
                continue;
            } else if (parameterType.getType() == MultipartFile.class) {
                params[i] = chain.getRequestFiles()[0];
                continue;
            }
            //请求与响应类型
            if (parameterType.getType() == HttpServletRequest.class) {
                params[i] = chain.getRequest();
                continue;
            }
            if (parameterType.getType() == HttpServletRequest.class) {
                params[i] = chain.getResponse();
                continue;
            }

            //@RequestParam 或 基础类型
            if (ConverterUtil.isPrimitive(parameterType.getType()) || parameterType.isAnnotationPresent(RequestParam.class) || parameterType.getType().isArray()) {

                String[] strings = getRequestParam(parameterType, parameterMap);
                if (strings == null) {
                    params[i] = null;
                    continue;
                }
                //基本类型;
                if (!parameterType.getType().isArray()) {
                    params[i] = ConverterUtil.convert(parameterType.getType(), strings[0]);
                } else {
                    //TODO 参数为 基本类型数组 未处理
                    params[i] = strings;
                }

            } else {
                Class<?> type = parameterType.getType();
                Object o = type.newInstance();
                Field[] declaredFields = type.getDeclaredFields();
                if (type.isAnnotationPresent(Param.class)) {
                    for (Field field : declaredFields) {
                        setParamByField(field, parameterMap, o);
                    }
                } else {
                    for (Field field : declaredFields) {
                        if (field.isAnnotationPresent(Param.class)) {
                            setParamByField(field, parameterMap, o);
                        }
                    }
                }
                params[i] = o;
            }
        }
        return params;
    }

    @Deprecated
    private void setParam(Field field, Map<String, String[]> parameterMap, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = getFieldName(field);
        String[] strings = parameterMap.get(fieldName);
        String methodName = "set" + StringUtil.firstCharToUpperCase(fieldName);
        Method curMethod = o.getClass().getMethod(methodName, field.getClass());
        curMethod.setAccessible(true);
        Object convert = ConverterUtil.convert(field.getClass(), strings[0]);
        curMethod.invoke(o, convert);
    }

    /**
     * 注入属性
     *
     * @param field        属性对象
     * @param parameterMap 请求参数map
     * @param o            对象
     * @throws NoSuchMethodException     无此方法
     * @throws InvocationTargetException 反射执行失败
     * @throws IllegalAccessException    方法处理
     */
    private void setParamByField(Field field, Map<String, String[]> parameterMap, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = getFieldName(field);
        String[] strings = parameterMap.get(fieldName);
        if (strings != null) {
            field.setAccessible(true);
            if (ConverterUtil.isPrimitive(field.getType())) {
                field.set(o, ConverterUtil.convert(field.getType(), strings[0]));
            }
        }
    }

    /**
     * 获取属性名称
     *
     * @param field 属性对象
     * @return 属性名称
     */
    private String getFieldName(Field field) {
        String fieldName = EMPTY_STRING;
        Param annotation = field.getAnnotation(Param.class);
        if (annotation != null) {
            String[] value = annotation.value();
            fieldName = value[0];
        }
        if (EMPTY_STRING.equals(fieldName)) {
            fieldName = field.getName();
        }
        return fieldName;
    }


    /**
     * 获取基础变量 数组(RequestParam) 或者 加了注解 RequestParam
     *
     * @param parameterType Parameter参数对象
     * @param parameterMap  Request parameterMap 请求参数对象
     * @return String[] 请求值
     */
    private String[] getRequestParam(Parameter parameterType, Map<String, String[]> parameterMap) {
        String paramName = EMPTY_STRING;
        RequestParam requestParam = parameterType.getAnnotation(RequestParam.class);
        if (requestParam != null) {
            paramName = requestParam.value()[0];
        }
        if (EMPTY_STRING.equals(paramName)) {
            paramName = parameterType.getName();
        }
        return parameterMap.get(paramName);
    }

    public void setFilterAdapter(FilterAdapter filterAdapter) {
        this.filterAdapter = filterAdapter;
    }

}
