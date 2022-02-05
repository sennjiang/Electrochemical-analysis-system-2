package bluedot.electrochemistry.commons;

import java.util.UUID;

/**
 * @author Senn
 * @Create 2021/12/16 18:58
 */
public class VerifyCodeMaker {

    /**
     * 创建 验证码
     * @return 验证码
     */
    public static String getVerifyCode() {
        // 要发送的验证码
        return UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0, 5);
    }
}
