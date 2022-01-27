package bluedot.electrochemistry.simplespring.mvc.processor.render.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.processor.render.ResultRender;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import bluedot.electrochemistry.simplespring.util.LogUtil;
import org.slf4j.Logger;

import java.io.PrintWriter;

/**
 * Json渲染器
 *
 * @author xxbb
 */
public class JsonResultRender implements ResultRender {
    private Object object;
    private Logger logger = LogUtil.getLogger();

    public JsonResultRender(Object object) {
        this.object = object;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        //设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        //响应流写入经过gson格式化之后的处理结果
        try (PrintWriter writer = requestProcessorChain.getResponse().getWriter()) {
            writer.write(JsonUtil.toJson(object));
            writer.flush();
        }
    }
}
