package bluedot.electrochemistry.service.pojo.vo;

/**
 * @author Senn
 * @create 2022/1/28 19:32
 */
public class AlgoApplyPageView {
    private Integer algoId;
    private Integer algoApplyId;
    private String ulrName;
    private String altName;
    private String algoName;
    /**
     * 算法上传时间 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;
    /**
     * 申请条例创建时间 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_apply_create;

    /**
     * 申请类型，
     * 0：删除算法申请；
     * 1：添加算法申请。
     */
    private Integer type;
}
