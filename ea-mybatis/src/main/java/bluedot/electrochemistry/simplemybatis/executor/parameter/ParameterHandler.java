package bluedot.electrochemistry.simplemybatis.executor.parameter;

import java.sql.PreparedStatement;

/**
 * 处理传入参数
 *
 * @Author zero
 * @Create 2022/2/11 14:10
 */
public interface ParameterHandler {
    /**
     * 设置参数
     *
     * @param paramPreparedStatement 预处理对象
     */
    void setParameters(PreparedStatement paramPreparedStatement);
}
