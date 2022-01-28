package bluedot.electrochemistry.service.pojo.domain;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
public class Operation {
    private Integer id;
    private Integer userId;
    /**
     * tinyint 转 info error warm
     * 0：info
     * 1: warm
     * 2: error
     */
    private String level;

}
