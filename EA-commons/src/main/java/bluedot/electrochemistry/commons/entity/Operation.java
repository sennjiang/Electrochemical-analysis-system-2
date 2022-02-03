package bluedot.electrochemistry.commons.entity;


import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
@Param
public class Operation {
    private Integer id;
    private Integer userId;
    /**
     * tinyint 转 info error warm
     * 0：info
     * 1: warm
     * 2: error
     */
    private String level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
