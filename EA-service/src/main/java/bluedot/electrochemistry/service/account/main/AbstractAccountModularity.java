package bluedot.electrochemistry.service.account.main;

import bluedot.electrochemistry.service.Lifecycle;
import bluedot.electrochemistry.service.account.VerifyTask;

/**
 * @author Sens
 * @param <T> 用户类
 */
public interface AbstractAccountModularity<T> extends Lifecycle {

    /**
     * 发送 验证码
     * @param account 用户
     * @return String 验证码
     */
    String sendVerifyCode(String account) ;

    /**
     * 验证用户是否存在
     */
    boolean verifyAccount(VerifyTask task);

    /**
     * 登录
     * @return T 用户实体类
     */
    T login(VerifyTask task);

    /**
     * 注册
     * @param t 用户实体类
     */
    void register(T t);

    /**
     * 修改个人信息
     * @param t 用户实体类
     */
    void updateAccountInfo(T t);

    /**
     * 创建用户
     * @param t 泛型
     */
    void addAccount(T t);

    /**
     * 冻结
     * @param ids id
     */
    void freezeAccounts(String[] ids);

    /**
     * 解冻
     * @param ids id
     */
    void unfreezeAccounts(String[] ids);

}
