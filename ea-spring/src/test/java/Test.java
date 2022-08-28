import bluedot.electrochemistry.utils.ClassUtil;
import bluedot.electrochemistry.utils.ConverterUtil;
import bluedot.electrochemistry.simplespring.core.annotation.Param;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Senn
 * @create 2022/1/22 19:06
 */
public class Test {
    private static final String EMPTY_STRING = "";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Set<Class<?>> classes = ClassUtil.extractPackageClass("bluedot.electrochemistry.service");
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
    }

    private void test3() throws Exception{
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("name",new String[]{"Senn"});
        parameterMap.put("age",new String[]{"18"});

        Class<?> type = User.class;
        Object o = type.newInstance();
        Field[] declaredFields = type.getDeclaredFields();
        if (type.isAnnotationPresent(Param.class)) {
            for (Field field : declaredFields) {
                setParamByField(field, parameterMap,o);
            }
        }else {
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Param.class)) {
                    setParamByField(field,parameterMap,o);
                }
            }
        }
        System.out.println(o);
    }

    private static void setParamByField(Field field, Map<String, String[]> parameterMap, Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String fieldName = getFieldName(field);
        String[] strings = parameterMap.get(fieldName);
        System.out.println("fieldName :" + fieldName);
        if (strings != null) {
            field.setAccessible(true);
            if (ConverterUtil.isPrimitive(field.getType())) {
                field.set(o, ConverterUtil.convert(field.getType(), strings[0]));
            }
        }
    }

    /**
     * 获取属性名称
     * @param field 属性对象
     * @return 属性名称
     */
    private static String getFieldName(Field field) {
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

    public void test1() {
        Class<TestParam> testParamClass = TestParam.class;
        Method[] declaredMethods = testParamClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod : " +  declaredMethod);
            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println("parameter : " + parameter.getType() + " name : " + parameter.getName());
                System.out.println(parameter.isAnnotationPresent(RequestParam.class));
            }
        }
    }
    public void test0() {
        String str = "qwer";
        System.out.println(str.toUpperCase(Locale.ROOT));
        Class<String> stringClass = String.class;
        System.out.println(stringClass.isAssignableFrom(String.class));

        String[] strs = {"1","2"};
        int[] ints = new int[10];
        Class<? extends int[]> aClass = ints.getClass();
        System.out.println(strs.getClass().isArray());
        System.out.println(strs.getClass().getComponentType());
        Class<?> componentType = aClass.getComponentType();
        Object o = Array.newInstance(componentType, strs.length);
    }
}
