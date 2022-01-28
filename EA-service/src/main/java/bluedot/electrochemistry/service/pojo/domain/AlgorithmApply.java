package bluedot.electrochemistry.service.pojo.domain;

/**
 * @author Senn
 * @create 2022/1/28 18:54
 */
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
}
