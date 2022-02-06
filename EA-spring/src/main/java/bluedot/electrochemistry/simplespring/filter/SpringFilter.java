package bluedot.electrochemistry.simplespring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

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
    default boolean beforeFilter(HttpServletRequest request, HttpServletResponse response) throws ExecutionException {
        return true;
    }

    /**
     * 请求处理之后
     * @param request request
     * @param response response
     * @param returnValue 返回值参数 无则null
     */
    default boolean afterFilter(HttpServletRequest request, HttpServletResponse response, Object returnValue) throws Exception {
        return true;
    }

}
