package bluedot.electrochemistry.web.filter;

import bluedot.electrochemistry.simplespring.core.annotation.AfterFilter;
import bluedot.electrochemistry.simplespring.filter.SpringFilter;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import bluedot.electrochemistry.web.controller.base.Result;
import bluedot.electrochemistry.web.core.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Senn
 * @create 2022/2/3 21:11
 */
@AfterFilter
public class PostFilter implements SpringFilter {

    @Override
    public boolean afterFilter(HttpServletRequest request, HttpServletResponse response, Object returnValue) throws Exception {
        if (returnValue == null) {
            PrintWriter writer = response.getWriter();
            Result result = new Result();
            result.setCode(HttpStatus.BAD_REQUEST_RESULT);
            result.setMessage("无效请求..");
            writer.write(JsonUtil.toJson(result));
        }
        return true;
    }
}
