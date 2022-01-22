package bluedot.electrochemistry.simplespring.mvc.processor.impl;

import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.processor.RequestProcessor;
import bluedot.electrochemistry.simplespring.util.LogUtil;
import org.slf4j.Logger;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class DoFileProcessor implements RequestProcessor {

    private final Logger logger = LogUtil.getLogger();

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        logger.debug("do fileProcessor to make file...");
        return true;
    }

}
