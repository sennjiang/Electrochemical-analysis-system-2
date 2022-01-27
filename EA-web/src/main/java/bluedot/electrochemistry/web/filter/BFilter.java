package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import bluedot.electrochemistry.web.util.LogUtil;
import org.slf4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Senn
 * @create 2022/1/27 18:05
 */
@BeforeFilter(2)
public class BFilter implements SpringFilter {

    private static final Logger LOGGER =  LogUtil.getLogger(RightFilter.class);

    @Override
    public boolean beforeFilter(ServletRequest request, ServletResponse response) {
        LOGGER.info("do BFilter .. ");
        return true;
    }
}
