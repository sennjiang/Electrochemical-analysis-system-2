package bluedot.electrochemistry.commons.entity;


import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.sql.Timestamp;

/**
 * @author Senn
 * @createDate 2021/12/16 19:20
 */
@Param
public class User {

    private Long id;

    private String name;

    /**
     * 0 女 1 男 2 不明
     */
    private Integer sex;

    private Timestamp birthday;

    private String email;

    private String portrait;

    private String password;

    /**
     * 1：正常 2：冻结
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
