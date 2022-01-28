package bluedot.electrochemistry.service.pojo.domain;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
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
}
