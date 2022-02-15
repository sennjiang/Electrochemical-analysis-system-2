package bluedot.electrochemistry.simplemybatis.mapping;

/**
 * mapper.xml映射文件中信息的封装类，一个对象封装一条sql语句相关信息
 *
 * @Author zero
 * @Create 2022/2/10 15:31
 */
public class MappedStatement {
    /**
     * mapper文件的namespace命名空间
     */
    private String namespace;
    /**
     * sql标签的id，和namespace一起组成唯一的标记
     */
    private String id;
    /**
     * sql标签的的类型：select/insert/update/delete，todo 写成枚举类
     */
    private String sqlType;
    /**
     * 查询结果的返回值类型
     */
    private String returnType;
    /**
     * sql语句
     */
    private String sql;

    public MappedStatement() {
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "namespace='" + namespace + '\'' +
                ", id='" + id + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", returnType='" + returnType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
