package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Senn
 * @create 2022/1/22 18:36
 */
@BeforeFilter
public class RightFilter implements SpringFilter {

    private static final Logger LOGGER =  LogUtil.getLogger(RightFilter.class);

    private final RequestURLAdapter adapter = new RequestURLAdapter();

    @Override
    public boolean beforeFilter(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("do right filter .. ");

        if (adapter.isWhiteUrl(request.getPathInfo())) {
            return true;
        }else {
            //TODO 权限查询
        }
        return true;
    }
}
