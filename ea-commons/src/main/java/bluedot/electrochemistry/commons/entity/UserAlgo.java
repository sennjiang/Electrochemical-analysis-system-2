package bluedot.electrochemistry.commons.entity;

import java.sql.Timestamp;

/**
 * @author Senn
 * @create 2022/2/10 20:15
 */
public class UserAlgo {
    private Long id;

    private Long userId;

    private Long algoId;

    private Integer algoType;

    private Timestamp gmtCreate;

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

    public Long getAlgoId() {
        return algoId;
    }

    public void setAlgoId(Long algoId) {
        this.algoId = algoId;
    }

    public Integer getAlgoType() {
        return algoType;
    }

    public void setAlgoType(Integer algoType) {
        this.algoType = algoType;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
