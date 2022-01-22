package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.annotation.BeforeFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

/**
 * @author Senn
 * @create 2022/1/22 18:36
 */
@BeforeFilter
public class RightFilter implements SpringFilter {

    @Override
    public void beforeFilter(HttpRequest request, HttpResponse response) {

    }
}
