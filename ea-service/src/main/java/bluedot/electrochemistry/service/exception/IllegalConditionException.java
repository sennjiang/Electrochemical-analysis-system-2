package bluedot.electrochemistry.service.exception;

/**
 * 非法查询条件异常，如 sql 注入
 * @author Senn
 * @create 2022/1/12 20:30
 */
public class IllegalConditionException extends Exception{

    public IllegalConditionException(String message) {
        super(message);
    }
}
