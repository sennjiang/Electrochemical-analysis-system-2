package bluedot.electrochemistry.web.controller.base;

import bluedot.electrochemistry.web.core.HttpStatus;
import com.google.gson.annotations.SerializedName;

/**
 * @author Senn
 * @createDate 2021/12/16 20:36
 */
public class Result {

    private int code;

    /**
     * 信息
     */
    @SerializedName("msg")
    private String message;

    /**
     * 数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(HttpStatus status) {
        this.code = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
