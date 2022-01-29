package bluedot.electrochemistry.simplespring.mvc;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.simplespring.mvc.processor.render.ResultRender;
import bluedot.electrochemistry.simplespring.mvc.processor.render.impl.DefaultResultRender;
import bluedot.electrochemistry.simplespring.mvc.processor.render.impl.ErrorResultRender;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class RequestProcessorChain {


    /**
     * 请求处理迭代器
     */
    private Iterator<RequestProcessor> requestProcessorIterator;
    /**
     * 请求request
     */
    private HttpServletRequest req;
    /**
     * 请求response
     */
    private HttpServletResponse resp;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String requestPath;
    /**
     * 请求参数
     */
    private Map<String ,String[]> requestParams;
    /**
     * 请求参数
     */
    private MultipartFile[] requestFiles;
    /**
     * 请求状态码
     */
    private int responseCode;
    /**
     * 请求结果渲染器
     */
    private ResultRender resultRender;
    /**
     * 日志
     */
    private static final Logger LOGGER = LogUtil.getLogger("spring.mvc");


    /**
     * 构造函数
     *
     * @param iterator 请求处理迭代器
     * @param req      req
     * @param resp     resp
     */
    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest req, HttpServletResponse resp) {
        this.requestProcessorIterator = iterator;
        this.req = req;
        this.resp = resp;
        this.requestMethod = req.getMethod();
        this.requestPath = req.getPathInfo();
        this.responseCode = HttpServletResponse.SC_OK;
    }

    /**
     * 以责任链的模式执行请求链
     */
    public void doRequestProcessorChain() {
        //通过迭代器遍历注册的请求助力器实现类列表
        try {
            while (requestProcessorIterator.hasNext()) {
                RequestProcessor requestProcessor = requestProcessorIterator.next();
                LOGGER.debug("this requestProcessor is {}", requestProcessor);
                //直到某个请求处理器执行后返回为 false 为止
                if (!requestProcessor.process(this)) {
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("执行出错 : {}",e.getMessage());
            setResultRender(new ErrorResultRender(e.getMessage()));
        }


    }

    /**
     * 执行处理器
     */
    public void doRender() {
        //如果请求处理器实现类未选择合适的渲染器，则使用默认渲染器
        if (null == this.resultRender) {
            this.resultRender = new DefaultResultRender();
        }
        try {
            //调用渲染器的render方法对结果进行渲染
            LOGGER.debug("using {} to render view", this.resultRender.getClass().getSimpleName());
            this.resultRender.render(this);
        } catch (Exception e) {
            LOGGER.error("doRender error:", e);
        }
    }


    public Iterator<RequestProcessor> getRequestProcessorIterator() {
        return requestProcessorIterator;
    }

    public void setRequestProcessorIterator(Iterator<RequestProcessor> requestProcessorIterator) {
        this.requestProcessorIterator = requestProcessorIterator;
    }

    public HttpServletRequest getRequest() {
        return req;
    }

    public void setRequest(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletResponse getResponse() {
        return resp;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ResultRender getResultRender() {
        return resultRender;
    }

    public void setResultRender(ResultRender resultRender) {
        this.resultRender = resultRender;
    }

    public Logger getLog() {
        return LOGGER;
    }

    public Map<String, String[]> getRequestParams() {
        if (this.requestParams == null) {
            requestParams = new HashMap<>();
        }
        return requestParams;
    }

    public void setRequestParams(Map<String, String[]> requestParams) {
        this.requestParams = requestParams;
    }

    public MultipartFile[] getRequestFiles() {
        return requestFiles;
    }

    public void setRequestFiles(MultipartFile[] requestFiles) {
        this.requestFiles = requestFiles;
    }

    @Override
    public String toString() {
        return "RequestProcessorChain{" +
                "requestProcessorIterator=" + requestProcessorIterator +
                ", req=" + req +
                ", resp=" + resp +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", responseCode=" + responseCode +
                ", resultRender=" + resultRender +
                '}';
    }

}
