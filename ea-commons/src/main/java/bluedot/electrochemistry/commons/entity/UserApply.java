package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.sql.Timestamp;

/**
 * @author Senn
 * @create 2022/1/28 18:53
 */
@Param
public class UserApply {
    private Long id;
    private Long userId;
    private String freeze_reason;
    private String unfreeze_reason;
    /**
     * 0 未审核
     * 1 审核通过
     * 2 审核未通过
     */
    private Integer status;

    private Timestamp gmtCreate;

    private Timestamp gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }
}
