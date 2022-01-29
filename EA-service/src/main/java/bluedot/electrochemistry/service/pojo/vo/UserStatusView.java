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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFreeze_reason() {
        return freeze_reason;
    }

    public void setFreeze_reason(String freeze_reason) {
        this.freeze_reason = freeze_reason;
    }

    public String getUnfreeze_reason() {
        return unfreeze_reason;
    }

    public void setUnfreeze_reason(String unfreeze_reason) {
        this.unfreeze_reason = unfreeze_reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(String gmt_modify) {
        this.gmt_modify = gmt_modify;
    }
}
