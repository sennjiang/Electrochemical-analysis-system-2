package bluedot.electrochemistry.commons.sender;

import javax.mail.MessagingException;

/**
 * @author Senn
 * @create 2022/2/3 22:28
 */
public class AbstractSender implements Sender{
    @Override
    public boolean sendMessage(String emailPath, String message) throws MessagingException {
        return false;
    }

    @Override
    public boolean checkContent(String content) {
        return false;
    }
}
