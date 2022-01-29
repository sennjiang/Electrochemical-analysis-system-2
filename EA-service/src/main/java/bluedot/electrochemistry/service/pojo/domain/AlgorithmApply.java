package bluedot.electrochemistry.service.pojo.domain;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/28 18:54
 */
@Param
public class AlgorithmApply {
    private Integer id;
    private Integer userId;
    private Integer algorithmId;
    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;
    /**
     * 申请类型，
     * 0：删除算法申请；
     * 1：添加算法申请。
     */
    private Integer type;

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

    public Integer getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
