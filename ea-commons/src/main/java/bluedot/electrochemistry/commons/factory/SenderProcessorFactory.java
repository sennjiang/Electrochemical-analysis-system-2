package bluedot.electrochemistry.commons.factory;

import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.sender.processor.SenderProcessor;
import bluedot.electrochemistry.simplemybatis.utils.LogUtils;
import bluedot.electrochemistry.utils.ConfigUtil;
import org.slf4j.Logger;

import java.util.Properties;

/**
 * @author Senn
 * @create 2022/2/8 19:29
 */
public class SenderProcessorFactory implements Lifecycle {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static Properties properties;

    private static final SenderProcessor PROCESSOR = new SenderProcessor(properties);

    public static SenderProcessor createSenderProcessor(){
        return PROCESSOR;
    }

    @Override
    public void init() {
        properties = ConfigUtil.doLoadConfig("commons.properties");
    }

    @Override
    public void destroy() {

    }
}
