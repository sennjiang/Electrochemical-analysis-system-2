package bluedot.electrochemistry.commons.entity.vo;

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

    public Integer getAlgoId() {
        return algoId;
    }

    public void setAlgoId(Integer algoId) {
        this.algoId = algoId;
    }

    public Integer getAlgoApplyId() {
        return algoApplyId;
    }

    public void setAlgoApplyId(Integer algoApplyId) {
        this.algoApplyId = algoApplyId;
    }

    public String getUlrName() {
        return ulrName;
    }

    public void setUlrName(String ulrName) {
        this.ulrName = ulrName;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_apply_create() {
        return gmt_apply_create;
    }

    public void setGmt_apply_create(String gmt_apply_create) {
        this.gmt_apply_create = gmt_apply_create;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
