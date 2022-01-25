package bluedot.electrochemistry.simplespring.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
    default void beforeFilter(ServletRequest request, ServletResponse response) {

    }

    /**
     * 请求处理之后
     * @param request request
     * @param response response
     * @param returnValue 返回值参数 无则null
     */
    default void afterFilter(ServletRequest request, ServletResponse response, Object returnValue) {

    }

}
