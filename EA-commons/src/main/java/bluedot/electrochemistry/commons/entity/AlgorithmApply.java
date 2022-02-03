package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/28 18:54
 */
@Param
public class AlgorithmApply {
    private Integer id;
    private Integer userId;
    private Integer algoName;

    /**
     * 申请类型，
     * 0：删除算法申请；
     * 1：添加算法申请。
     */
    private Integer algoType;

    /**
     * 申请类型，
     * 0：删除算法申请；
     * 1：添加算法申请。
     */
    private Integer applyType;

    /**
     * 0 未审核
     * 1 通过
     * 2 失败
     */
    private Integer status;

    /**
     * 算法地址
     */
    private String path;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAlgoName() {
        return algoName;
    }

    public void setAlgoName(Integer algoName) {
        this.algoName = algoName;
    }

    public Integer getAlgoType() {
        return algoType;
    }

    public void setAlgoType(Integer algoType) {
        this.algoType = algoType;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
