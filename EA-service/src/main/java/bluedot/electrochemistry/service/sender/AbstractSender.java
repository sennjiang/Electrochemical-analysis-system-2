package bluedot.electrochemistry.service.sender;

import bluedot.electrochemistry.common.LogUtil;
import org.slf4j.Logger;

/**
 * @author Senn
 * @create 2021/12/16 18:58
 */
public abstract class AbstractSender implements Sender {

    protected static final Logger logger = LogUtil.getLogger(AbstractSender.class);

    @Override
    public boolean checkContent(String content) {
        return content.length() < 200 ;
    }
}
