package bluedot.electrochemistry.commons.sender;

import javax.mail.MessagingException;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface Sender {
    //发送消息
    boolean sendMessage(String emailPath, String message) throws MessagingException;
    //内容验证
    boolean checkContent(String content);
}
