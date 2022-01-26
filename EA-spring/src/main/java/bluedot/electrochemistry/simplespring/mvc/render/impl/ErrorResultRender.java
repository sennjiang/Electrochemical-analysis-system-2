package bluedot.electrochemistry.simplespring.mvc.render.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.render.ResultRender;
import bluedot.electrochemistry.simplespring.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/1/26 19:48
 */
public class ErrorResultRender implements ResultRender {

    private String errorMessage;

    public ErrorResultRender(String message) {
        this.errorMessage = message;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("code",404);
        map.put("message",errorMessage);
        requestProcessorChain.getResp().setCharacterEncoding("GBK");
        requestProcessorChain.getResp().getWriter().write(JsonUtil.toJson(map));
    }
}
