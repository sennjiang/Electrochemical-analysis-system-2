package bluedot.electrochemistry.service.pojo.domain;

/**
 * @author Senn
 * @create 2022/1/13 12:13
 */
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
}
