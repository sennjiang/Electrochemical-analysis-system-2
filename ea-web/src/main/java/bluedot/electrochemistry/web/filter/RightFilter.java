package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.commons.factory.CacheExecutorFactory;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author Senn
 * @create 2022/1/22 18:36
 */
@BeforeFilter
public class RightFilter implements SpringFilter {

    private static final Logger LOGGER =  LogUtil.getLogger(RightFilter.class);

    private final RequestURLAdapter adapter = (RequestURLAdapter) BeanContainer.getInstance().getBean(RequestURLAdapter.class);


    @Override
    public boolean beforeFilter(HttpServletRequest request, HttpServletResponse response) throws ExecutionException {
        LOGGER.info("do right filter .. ");
        if (adapter.isWhiteUrl(request.getPathInfo())) {
            LOGGER.info("a white url do nothing ...");
            return true;
        }else {
//            String requestURI = request.getRequestURI();
//            String[] userIds = request.getParameterValues("userId");
//            String[] roles = arrayCache.get(userIds[0]);
//            for (String role : roles) {
//                String[] strings = arrayCache.get(role);
//                for (String string : strings) {
//                    if (requestURI.equals(string)) return true;
//                }
//            }
        }
        return false;
    }
}
