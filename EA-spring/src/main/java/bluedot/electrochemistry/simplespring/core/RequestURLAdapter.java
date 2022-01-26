package bluedot.electrochemistry.simplespring.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/1/22 17:44
 */
public class RequestURLAdapter {

    private Map<String, Method> urlMap = new HashMap<>();

    private Map<String, Method> whiteUrlMap = new HashMap<>();

    private Map<String, Class<?>> classMap = new HashMap<>();

    public void putUrl(String url, Method method) {
        urlMap.put(url,method);
    }

    public void putWhiteUrl(String url, Method method) {
        whiteUrlMap.put(url,method);
    }

    public Method getUrl(String url) {
        return urlMap.getOrDefault(url,null);
    }

    public void putClass(String url, Class<?> clazz) {
        classMap.put(url,clazz);
    }

    public Class<?> getClass(String url) {
        return classMap.getOrDefault(url,null);
    }

    public Method getWhiteUrl(String url) {
        return whiteUrlMap.getOrDefault(url,null);
    }
    public boolean isWhiteUrl(String url) {
        return whiteUrlMap.containsKey(url);
    }
}
