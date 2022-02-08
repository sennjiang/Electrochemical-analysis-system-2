package bluedot.electrochemistry.commons.sender.handler;

import bluedot.electrochemistry.commons.NotNullable;

/**
 * @author Senn
 * @create 2022/2/7 20:02
 */
public class Message implements NotNullable {
    private String account;
    private String content;
    private SendType type;

    public Message(String account, String content, SendType type) {
        this.account = account;
        this.content = content;
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SendType getType() {
        return type;
    }

    public void setType(SendType type) {
        this.type = type;
    }

    @Override
    public boolean isNull() {
        return account == null || content == null || type == null;
    }
}
