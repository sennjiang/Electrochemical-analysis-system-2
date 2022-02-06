package bluedot.electrochemistry.commons.entity.vo;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlgoname() {
        return algoname;
    }

    public void setAlgoname(String algoname) {
        this.algoname = algoname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
