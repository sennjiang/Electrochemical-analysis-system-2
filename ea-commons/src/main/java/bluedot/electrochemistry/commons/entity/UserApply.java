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

    private Timestamp gmt_create;

    private Timestamp gmt_modify;

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

    public Timestamp getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Timestamp gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Timestamp getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(Timestamp gmt_modify) {
        this.gmt_modify = gmt_modify;
    }
}
