package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.exception.IllegalConditionException;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @create 2022/1/12 20:23
 */
public abstract class DefaultCondition implements Conditional{

    public static final String SQL_AND = "and";

    public static final String SQL_OR = "or";

    public static final String SQL_EQUAL  = "=";

    public static final String SQL_LIMIT = "limit";

    protected SelectType type;

    /**
     * 分页开始
     */
    @Param
    protected Integer pageStart;

    /**
     * 分页大小
     */
    @Param
    protected Integer pageSize;

    /**
     * 搜索框内容
     */
    @Param
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

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
