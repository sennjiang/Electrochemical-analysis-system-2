package bluedot.electrochemistry.commons.entity;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/13 12:13
 */
@Param
public class EaFile {
    private Integer id;
    private Integer userId;
    private String name;
    private String path;
    private Integer size;
    /**
     * 0 正常
     * 1 移除
     * 2 删除
     */
    private Integer status;
    private Integer type;
    /**
     * 数据起始x0
     */
    private String data_start;
    /**
     * 数据起始x1
     */
    private String data_end;
    /**
     * 数据峰值 , 隔开
     */
    private String data_peek;
    /**
     * 数据谷值 , 隔开
     */
    private String data_bottom;
    /**
     * 数据精度
     */
    private Integer data_precision;
    /**
     * 数据切点 ，隔开
     */
    private String tangency;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData_start() {
        return data_start;
    }

    public void setData_start(String data_start) {
        this.data_start = data_start;
    }

    public String getData_end() {
        return data_end;
    }

    public void setData_end(String data_end) {
        this.data_end = data_end;
    }

    public String getData_peek() {
        return data_peek;
    }

    public void setData_peek(String data_peek) {
        this.data_peek = data_peek;
    }

    public String getData_bottom() {
        return data_bottom;
    }

    public void setData_bottom(String data_bottom) {
        this.data_bottom = data_bottom;
    }

    public Integer getData_precision() {
        return data_precision;
    }

    public void setData_precision(Integer data_precision) {
        this.data_precision = data_precision;
    }

    public String getTangency() {
        return tangency;
    }

    public void setTangency(String tangency) {
        this.tangency = tangency;
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
