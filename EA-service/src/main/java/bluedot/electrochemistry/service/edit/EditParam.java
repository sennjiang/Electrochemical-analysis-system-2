package bluedot.electrochemistry.service.edit;

/**
 * @author Senn
 * @create 2022/1/13 13:50
 */
public class EditParam<T> {

    public static final String UPDATE = "update";

    public static final String DELETE = "delete";

    public static final String ADD = "add";

    /**
     * 实体类
     */
    private T t;

    /**
     * 策略
     */
    private String type;

    public EditParam(T t, String type) {
        this.t = t;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public T getT() {
        return this.t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setType(String type) {
        this.type = type;
    }
}
