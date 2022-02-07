package bluedot.electrochemistry.commons.sender.handler;

/**
 * @author Senn
 * @create 2022/2/7 20:02
 */
public class Mail {
    private String account;
    private String message;

    public Mail(String account, String message) {
        this.account = account;
        this.message = message;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
