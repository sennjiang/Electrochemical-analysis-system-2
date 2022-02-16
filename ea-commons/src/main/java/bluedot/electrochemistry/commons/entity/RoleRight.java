package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.time.LocalDateTime;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
@Param
public class RoleRight {
    private Integer id;
    private Integer roleId;
    private Integer rightId;
    private LocalDateTime gmt_create;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public LocalDateTime getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(LocalDateTime gmt_create) {
        this.gmt_create = gmt_create;
    }
}
