package bluedot.electrochemistry.simplespring.mvc.render.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.render.ResultRender;
import bluedot.electrochemistry.simplespring.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  默认渲染器
 * @author Senn
 */
public class DefaultResultRender implements ResultRender {
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("code",401);
        map.put("message","用户未登录！请先登录");
        requestProcessorChain.getResp().setCharacterEncoding("GBK");
        requestProcessorChain.getResp().getWriter().write(JsonUtil.toJson(map));
    }
}
