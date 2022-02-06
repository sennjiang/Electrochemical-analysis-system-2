package bluedot.electrochemistry.web.controller.base;

import bluedot.electrochemistry.web.core.HttpStatus;

/**
 * @author Senn
 * @createDate 2021/12/16 20:36
 */
public class Result {

    private static final Integer STATUS_CODE = 10;
    /**
     * 存储 状态码 与 list大小 或其他数字
     * 前 10 位存储 状态码 = codeNum % 1024
     * 后22位存大小 = codeNum >> 10
     * 按位存取
     */
    private int codeNum;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public void setCode(int num) {
        codeNum += num;
    }
    public void setCode(HttpStatus status) {
        codeNum += status.value();
    }

    public void setSize(int num) {
        codeNum +=  (num <<= STATUS_CODE);
    }

    public int getCodeNum() {
        return codeNum;
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
