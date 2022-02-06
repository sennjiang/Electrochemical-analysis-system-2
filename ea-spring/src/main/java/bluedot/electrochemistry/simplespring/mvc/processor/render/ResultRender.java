package bluedot.electrochemistry.simplespring.mvc.processor.render;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;

/**
 * 渲染请求结果
 * @author Senn
 * @create 2022/1/22 17:03
 */
public interface ResultRender {
    /**
     * 执行渲染
     * @param requestProcessorChain 请求责任链
     * @throws Exception 异常
     */
     void render(RequestProcessorChain requestProcessorChain) throws Exception;
}
