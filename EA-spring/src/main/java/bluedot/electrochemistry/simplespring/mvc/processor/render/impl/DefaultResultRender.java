package bluedot.electrochemistry.simplespring.mvc.processor.render.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.processor.render.ResultRender;
import bluedot.electrochemistry.simplespring.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  默认渲染器
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class DefaultResultRender implements ResultRender {

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws IOException {

        Map<String,Object> map = new HashMap<>();
        map.put("code",401);
        map.put("message","do nothing");
        requestProcessorChain.getResponse().setCharacterEncoding("GBK");
        requestProcessorChain.getResponse().getWriter().write(JsonUtil.toJson(map));
    }

}
