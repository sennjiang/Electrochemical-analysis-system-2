package bluedot.electrochemistry.simplespring.mvc.render.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.render.ResultRender;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源未找到异常
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class ResourceNotFoundResultRender implements ResultRender {

    private String httpMethod;
    private String httpPath;
    public ResourceNotFoundResultRender(String method, String path) {
        this.httpMethod = method;
        this.httpPath = path;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResp().sendError(HttpServletResponse.SC_NOT_FOUND,
                "获取不到对应的请求资源：请求路径[" + httpPath + "]" + "请求方法[" + httpMethod + "]");
    }
}
