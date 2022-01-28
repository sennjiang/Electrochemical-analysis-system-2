package bluedot.electrochemistry.service.pojo.domain;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @createDate 2021/12/16 19:20
 */
@Param
public class User {

    @Param
    private String name;

    @Param("a")
    private Integer age;
    /**
     * 0 女 1 男 2 不明
     */
    private Integer sex;
    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String birthday;

    private String email;

    private String portrait;

    private String password;

    /**
     * 1：正常 2：冻结
     */
    private Integer status;

    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_create;

    /**
     * 转换 timestamp 为 String yyyy-MM-dd:HH:mm:ss
     */
    private String gmt_modify;

}
