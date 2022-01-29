package bluedot.electrochemistry.service.pojo.domain;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/28 18:55
 */
@Param
public class Right {
    private Integer id;
    private String route;

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }
}
