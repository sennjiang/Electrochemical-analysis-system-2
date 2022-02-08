package bluedot.electrochemistry.commons.sender.handler;

/**
 * @author Senn
 * @create 2022/2/8 19:36
 */
public enum SendType {
    /**
     * 验证码
     */
    VERIFY_CODE,
    /**
     * 用户状态冻结通知
     */
    USER_FREEZE_INFORM,
    /**
     * 用户解冻通知
     */
    USER_UNFREEZE_INFORM,

    /**
     * 算法申请通知
     */
    ALGO_APPLY_INFORM,
    /**
     * 算法审核通知
     */
    ALGO_APPLY_CHECK_INFORM,

    /**
     * 算法库申请通知
     */
    ALGO_LIBRARY_APPLY_INFORM,

    /**
     * 算法库审核通知
     */
    ALGO_LIBRARY_APPLY_CHECK_INFORM,

    /**
     * 其他
     */
    OTHER
}
