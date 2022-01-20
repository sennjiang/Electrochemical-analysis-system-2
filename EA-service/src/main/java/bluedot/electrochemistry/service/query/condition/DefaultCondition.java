package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.exception.IllegalConditionException;
import bluedot.electrochemistry.exception.IllegalIndexException;

/**
 * @author Senn
 * @create 2022/1/12 20:23
 */
public abstract class DefaultCondition implements Conditional{

    public static final String SQL_AND = "and";

    public static final String SQL_OR = "or";

    public static final String SQL_EQUAL  = "=";

    public static final String SQL_LIMIT = "limit";

    /**
     * 分页开始
     */
    protected Integer pageStart;

    /**
     * 分页大小
     */
    protected Integer pageSize;

    /**
     * 搜索框内容
     */
    protected String content;

    @Override
    public String decodeCondition() {
        return null;
    }

    @Override
    public boolean checkCondition() {
        try {
            check();
            checkOther();
            return true;
        } catch (IllegalIndexException | IllegalConditionException e) {
            return false;
        }
    }

    protected void check() throws IllegalIndexException , IllegalConditionException{
        if (pageStart <= 0 ) {
            throw new IllegalIndexException("非法索引");
        }
        if (content.contains(SQL_AND) || content.contains(SQL_OR) || content.contains(SQL_EQUAL)) {
            throw new IllegalConditionException("非法条件");
        }
    }

    /**
     * 预留的检查方法 由子类实现扩展
     */
    protected void checkOther() {

    }
}
