package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.exception.IllegalConditionException;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.Table;

/**
 * @author Senn
 * @Create 2021/12/16 18:58
 */
public interface Conditional {

    /**
     *  获取 sql 预处理语句
     * @return sql语句
     */
    String decodeCondition();


    Object[] getQueryParams();
    /**
     *  验证条件 防止sql注入
     * @return sql语句
     */
    boolean checkCondition() throws IllegalIndexException , IllegalConditionException;

    /**
     * 获取查询类型
     */
    SelectType getSelectType();

    /**
     * 获取查询表
     */
    Table getTable();
}
