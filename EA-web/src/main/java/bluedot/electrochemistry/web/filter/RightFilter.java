package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Senn
 * @create 2022/1/22 18:36
 */
@BeforeFilter
public class RightFilter implements SpringFilter {

    @Override
    public void beforeFilter(ServletRequest request, ServletResponse response) {
        SpringFilter.super.beforeFilter(request, response);
    }
}
