package bluedot.electrochemistry.commons.entity;

import java.time.LocalDateTime;

/**
 * @author Senn
 * @create 2022/2/10 20:15
 */
public class UserAlgo {
    Integer id;

    Integer userId;

    Integer algoId;

    Integer algoType;

    private LocalDateTime gmt_create;

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

    public Integer getAlgoId() {
        return algoId;
    }

    public void setAlgoId(Integer algoId) {
        this.algoId = algoId;
    }

    public Integer getAlgoType() {
        return algoType;
    }

    public void setAlgoType(Integer algoType) {
        this.algoType = algoType;
    }

    public LocalDateTime getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(LocalDateTime gmt_create) {
        this.gmt_create = gmt_create;
    }
}
