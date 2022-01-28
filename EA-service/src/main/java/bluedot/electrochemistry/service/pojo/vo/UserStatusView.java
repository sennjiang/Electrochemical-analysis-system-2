package bluedot.electrochemistry.service.pojo.vo;

/**
 * @author Senn
 * @create 2022/1/28 19:31
 */
public class UserStatusView {
    private Integer id;
    private String name;
    private String email;
    private String freeze_reason;
    private String unfreeze_reason;
    /**
     * 0 未审核
     * 1 审核通过
     * 2 审核未通过
     */
    private Integer status;
    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;

    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_modify;
}
