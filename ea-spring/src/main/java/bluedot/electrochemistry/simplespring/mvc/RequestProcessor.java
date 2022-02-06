package bluedot.electrochemistry.simplespring.mvc;

/**
 * 请求执行器
 * @author Senn
 * @create 2022/1/22 17:03
 */
public interface RequestProcessor {
    /**
     * 处理请求
     *
     * @param requestProcessorChain
     * @return
     * @throws Exception
     */
    boolean process(RequestProcessorChain requestProcessorChain) throws Exception;
}
