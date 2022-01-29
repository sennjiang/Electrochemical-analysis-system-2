package bluedot.electrochemistry.simplespring.mvc.processor.impl;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.render.impl.ResourceResultRender;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class StaticResourceRequestProcessor implements RequestProcessor {
    private static final String DEFAULT_TOMCAT_SERVLET = "default";
    private static final String STATIC_RESOURCE_PREFIX = "/static/";
    private static final String ASSETS_RESOURCE_PREFIX = "/assets/";
    private static final String HTML_RESOURCE_SUFFIX = ".html";
    private static final String VUE_RESOURCE_SUFFIX = ".vue";
    private static final String JSP_RESOURCE_SUFFIX = ".jsp";
    private static final String TXT_RESOURCE_SUFFIX = ".txt";
    private static final String JAVA_RESOURCE_SUFFIX = ".java";
    private static final String ICO_RESOURCE_SUFFIX = ".ico";


    private static final Logger LOGGER = LogUtil.getLogger("spring.mvc.processor");
    /**
     * tomcat默认的请求派发器
     */
    RequestDispatcher defaultDispatcher;

    public StaticResourceRequestProcessor(ServletContext servletContext) {
        this.defaultDispatcher = servletContext.getNamedDispatcher(DEFAULT_TOMCAT_SERVLET);
        if (null == defaultDispatcher) {
            LOGGER.error("StaticResourceRequestProcessor constructor error");
            throw new RuntimeException("There is no default tomcat servlet");
        }
        LOGGER.debug("The default servlet for static resource is {}", DEFAULT_TOMCAT_SERVLET);
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        //通过请求路径判断是否为静态资源
        if (isStaticResource(requestProcessorChain.getRequestPath())) {
            //如果是静态资源，则交给default servlet处理
            LOGGER.debug("static requestPath: {}", requestProcessorChain.getRequestPath());
            defaultDispatcher.forward(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
            requestProcessorChain.setResultRender(new ResourceResultRender());
            return false;
        }
        return true;
    }

    /**
     * 判断该路径是否为静态资源路径
     *
     * @param requestPath 请求路径
     * @return 否为静态资源路径
     */
    private boolean isStaticResource(String requestPath) {
        return requestPath.startsWith(STATIC_RESOURCE_PREFIX)
                || requestPath.startsWith(ASSETS_RESOURCE_PREFIX)
                || requestPath.endsWith(HTML_RESOURCE_SUFFIX)
                || requestPath.endsWith(VUE_RESOURCE_SUFFIX)
                || requestPath.endsWith(JSP_RESOURCE_SUFFIX)
                || requestPath.endsWith(TXT_RESOURCE_SUFFIX)
                || requestPath.endsWith(ICO_RESOURCE_SUFFIX)
                || requestPath.endsWith(JAVA_RESOURCE_SUFFIX);
    }
}
