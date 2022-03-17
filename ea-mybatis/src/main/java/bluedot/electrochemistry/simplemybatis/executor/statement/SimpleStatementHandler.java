package bluedot.electrochemistry.simplemybatis.executor.statement;

import bluedot.electrochemistry.simplemybatis.mapping.MappedStatement;
import bluedot.electrochemistry.simplemybatis.utils.LogUtils;
import bluedot.electrochemistry.simplemybatis.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 负责处理jdbc的statement（此处是PreparedStatement）的交互和对sql的预处理工作
 * @Author zero
 * @Create 2022/2/11 13:41
 */
public class SimpleStatementHandler implements StatementHandler {
    /**
     * 正则匹配语句中的#{}标签
     */
    private static final Pattern PARAM_PATTERN = Pattern.compile("#\\{([^{}]*)}");
    /**
     * 封装sql语句的对象
     */
    private final MappedStatement mappedStatement;


    public SimpleStatementHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    /**
     * 将预处理sql语句中的#{}替换成？
     *
     * @param connection 连接
     * @return 处理好的sql语句
     * @throws SQLException sql异常
     */
    @Override
    public PreparedStatement prepared(Connection connection) throws SQLException {
        String originSql = mappedStatement.getSql();
        if (StringUtils.isNotEmpty(originSql)) {
            //替换#{},预处理，防止sql注入
            return connection.prepareStatement(parseSymbol(originSql));
        } else {
            LogUtils.getLogger().error("origin sql is null.");
            throw new RuntimeException("origin sql is null.");
        }
    }

    /**
     * 数据库的查询方法
     *
     * @param preparedStatement statement对象
     * @return 结果集
     * @throws SQLException sql异常
     */
    @Override
    public ResultSet query(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    /**
     * 数据库的增删改方法
     * @param preparedStatement statement对象
     * @return  数据库影响行数
     * @throws SQLException sql异常
     */
    @Override
    public int update(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeUpdate();
    }

    /**
     * 将SQL语句中的#{}替换为？，源码中是在SqlSourceBuilder类中解析的
     *
     * @param originSql 原始的sql语句
     * @return 处理好的替换好？的sql语句
     */
    private static String parseSymbol(String originSql) {
        originSql = originSql.trim();
        Matcher matcher = PARAM_PATTERN.matcher(originSql);
        return matcher.replaceAll("?");
    }

}
