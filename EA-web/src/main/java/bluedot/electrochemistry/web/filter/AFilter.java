package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.simplespring.core.annotation.AfterFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import org.slf4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * test TODO 删除
 * @author Senn
 * @create 2022/1/25 20:54
 */
@AfterFilter
public class AFilter implements SpringFilter {

    private static final Logger LOGGER =  LogUtil.getLogger(RightFilter.class);


    @Override
    public boolean afterFilter(ServletRequest request, ServletResponse response, Object returnValue) {
        LOGGER.info("do after filter returnValue : {}",returnValue);
        return true;
    }
}
