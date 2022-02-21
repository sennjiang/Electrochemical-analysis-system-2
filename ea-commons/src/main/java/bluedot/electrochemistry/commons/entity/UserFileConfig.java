package bluedot.electrochemistry.commons.entity;

import java.time.LocalDateTime;

/**
 * 用户文件配置PO类
 * @Author zero
 * @Create 2022/2/16 22:40
 */
public class UserFileConfig {
    /**
     * 编号
     */
    private Long id;

    /**
     * 个人文件数量
     */
    private Integer fileCount;

    /**
     * 回收站文件数量
     */
    private Integer binCount;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 删除类型，映射关系如下：
     * 0->删除最早的文件(默认)
     * 1->删除最大的文件
     */
    private Integer deleteType;

    /**
     * 上传个人算法数量
     */
    private Integer alogCount;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;

    public UserFileConfig() {
    }

    public UserFileConfig(Long id, Integer fileCount, Integer binCount, Long userId, Integer deleteType, Integer alogCount, LocalDateTime gmtModify) {
        this.id = id;
        this.fileCount = fileCount;
        this.binCount = binCount;
        this.userId = userId;
        this.deleteType = deleteType;
        this.alogCount = alogCount;
        this.gmtModify = gmtModify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Integer getBinCount() {
        return binCount;
    }

    public void setBinCount(Integer binCount) {
        this.binCount = binCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(Integer deleteType) {
        this.deleteType = deleteType;
    }

    public Integer getAlogCount() {
        return alogCount;
    }

    public void setAlogCount(Integer alogCount) {
        this.alogCount = alogCount;
    }

    public LocalDateTime getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(LocalDateTime gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "UserFileConfig{" +
                "id=" + id +
                ", fileCount=" + fileCount +
                ", binCount=" + binCount +
                ", userId=" + userId +
                ", deleteType=" + deleteType +
                ", alogCount=" + alogCount +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
