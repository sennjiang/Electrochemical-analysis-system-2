package bluedot.electrochemistry.service.sender;

import bluedot.electrochemistry.simplespring.util.LogUtil;
import org.slf4j.Logger;

/**
 * @author Senn
 * @create 2021/12/16 18:58
 */
public abstract class AbstractSender implements Sender {

    protected Logger logger = LogUtil.getLogger();

    @Override
    public boolean checkContent(String content) {
        return content.length() < 200 ;
    }
}
