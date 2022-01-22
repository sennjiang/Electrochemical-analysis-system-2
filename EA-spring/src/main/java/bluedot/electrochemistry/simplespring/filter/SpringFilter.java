package bluedot.electrochemistry.simplespring.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

/**
 * @author Senn
 * @create 2022/1/22 16:59
 */
public interface SpringFilter {
    /**
     * 请求之前
     * @param request request
     * @param response response
     */
    default void beforeFilter(HttpRequest request, HttpResponse response) {

    }

    /**
     * 请求处理之后
     * @param request request
     * @param response response
     * @param returnValue 返回值参数 无则null
     */
    default void afterFilter(HttpRequest request, HttpResponse response, Object returnValue) {

    }

}
