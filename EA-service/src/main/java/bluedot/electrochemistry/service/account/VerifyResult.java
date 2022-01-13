package bluedot.electrochemistry.service.account;

import bluedot.electrochemistry.pojo.domain.User;

/**
 * @author Senn
 * @create 2022/1/13 14:49
 */
public class VerifyResult {

    public static final String LOGIN = "login";

    public static final String CHECK_ACCOUNT = "check";

    private String type;

    private boolean flag = true;

    private User user;

    public VerifyResult(String type, boolean flag, User user) {
        this.type = type;
        this.flag = flag;
        this.user = user;
    }

    public VerifyResult() {
    }

    public String getType() {
        return type;
    }

    public boolean isFlag() {
        return flag;
    }

    public User getUser() {
        return user;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
