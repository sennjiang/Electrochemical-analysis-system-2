package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
@Param
public class SystemFile {
    private Integer id;
    private Integer userId;
    /**
     * 记录此次备份的名称
     */
    private String name;
    private String path;
    /**
     * 此次备份的备注信息
     */
    private String note;
    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }
}
