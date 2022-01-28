package bluedot.electrochemistry.service.pojo.vo;

/**
 * @author Senn
 * @create 2022/1/28 19:31
 */
public class AlgoPageView {
    /**
     * 算法id
     */
    private Integer id;
    private String username;
    private String algoname;
    private String uuid;
    /**
     * 算法创建时间 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;
    /**
     * 算法类型
     * 0：曲线平滑算法
     * 1：滤波处理算法
     */
    private Integer type;
    /**
     * 0：未上架,
     * 1：上架。
     */
    private Integer isUsed;
}
