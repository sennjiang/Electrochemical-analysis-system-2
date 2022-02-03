package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.annotation.AfterFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Senn
 * @create 2022/2/3 21:11
 */
@AfterFilter
public class PostFilter implements SpringFilter {

    @Override
    public boolean afterFilter(HttpServletRequest request, HttpServletResponse response, Object returnValue) throws Exception {
        if (returnValue == null) {
            throw new Exception("请求无结果！");
        }
        return true;
    }
}
