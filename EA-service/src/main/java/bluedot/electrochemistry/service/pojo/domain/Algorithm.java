package bluedot.electrochemistry.service.pojo.domain;

/**
 * @author Senn
 * @create 2022/1/28 19:04
 */
public class Algorithm {
    private Integer id;
    private Integer userId;
    private String name;
    private String uuid;
    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
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
