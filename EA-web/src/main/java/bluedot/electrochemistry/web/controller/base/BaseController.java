package bluedot.electrochemistry.web.controller.base;

/**
 * @author Senn
 * @create 2022/1/20 20:47
 */
public abstract class BaseController {

    /**
     * @param msg 失败的消息
     * @return {Object}
     */
    public Result renderError(String msg) {
        Result result = new Result();
        result.setMessage(msg);
        return result;
    }

    /**
     * @return Result
     */
    public Result renderBadRequest() {
        Result result = new Result();
        result.setMessage("bad request.");
        return result;
    }

    /**
     *
     * @param msg 失败的消息
     * @param code 响应码
     * @return Result
     */
    public Result renderError(String msg, int code) {
        Result result = new Result();
        result.setMessage(msg);
        result.setCode(code);
        return result;
    }

    /**
     * @return {Object}
     */
    public Result renderSuccess() {
        Result result = new Result();
        result.setCode(200);
        return result;
    }
    /**
     *
     * @param message 返回信息
     * @return result
     */
    public Result renderSuccess(String message) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    /**
     *
     * @param message 返回信息
     * @param object 返回值
     * @return result
     */
    public Result renderSuccess(String message, Object object) {
        Result result = new Result();
        result.setData(object);
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    /**
     *
     * @param message 返回信息
     * @param length 长度
     * @param object 返回值
     * @return result
     */
    public Result renderSuccess(String message, int length , Object object) {
        Result result = new Result();
        result.setData(object);
        result.setCode(200);
        return result;
    }

}
