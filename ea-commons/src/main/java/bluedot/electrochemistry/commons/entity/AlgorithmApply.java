package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.time.LocalDateTime;

/**
 * @author Senn
 * @create 2022/1/28 18:54
 */
@Param
public class AlgorithmApply {
    private Long id;
    private Long userId;
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

    private LocalDateTime gmt_create;

    private LocalDateTime gmt_modify;

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

    public LocalDateTime getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(LocalDateTime gmt_create) {
        this.gmt_create = gmt_create;
    }

    public LocalDateTime getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(LocalDateTime gmt_modify) {
        this.gmt_modify = gmt_modify;
    }
}
