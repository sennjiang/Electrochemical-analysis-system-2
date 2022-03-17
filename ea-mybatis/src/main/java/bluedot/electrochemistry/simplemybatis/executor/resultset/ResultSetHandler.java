package bluedot.electrochemistry.simplemybatis.executor.resultset;

import java.sql.ResultSet;
import java.util.List;

/**
 * 结果集处理，负责将resultSet集合转换为List
 *
 * @Author zero
 * @Create 2022/2/11 14:55
 */
public interface ResultSetHandler {
    /**
     * 对查询结果集进行封装，封装成对应类的List集合
     *
     * @param resultSet 结果集
     * @param <E>       泛型
     * @return 对应类的List集合
     */
    <E> List<E> handlerResultSet(ResultSet resultSet);
}
