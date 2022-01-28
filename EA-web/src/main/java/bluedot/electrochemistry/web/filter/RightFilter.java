package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import bluedot.electrochemistry.web.util.LogUtil;
import org.slf4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Senn
 * @create 2022/1/22 18:36
 */
@BeforeFilter
public class RightFilter implements SpringFilter {

    private static final Logger LOGGER =  LogUtil.getLogger(RightFilter.class);

    RequestURLAdapter adapter = new RequestURLAdapter();

    @Override
    public boolean beforeFilter(ServletRequest request, ServletResponse response) {
        LOGGER.info("do right filter .. ");
        if (adapter.isWhiteUrl("/user/login")) {

        }else {
            //权限
        }
        return true;
    }
}
