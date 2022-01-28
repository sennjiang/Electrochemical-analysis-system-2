package bluedot.electrochemistry.service.query;

/**
 * @author Senn
 * @create 2022/1/28 18:27
 */
public class QueryParam {

    SelectType selectType;

    Table table;

    String preparedSql;

    Object[] params;

    public QueryParam(SelectType selectType, Table table, String preparedSql, Object[] params) {
        this.selectType = selectType;
        this.table = table;
        this.preparedSql = preparedSql;
        this.params = params;
    }

    public SelectType getSearchType() {
        return selectType;
    }

    public void setSearchType(SelectType selectType) {
        this.selectType = selectType;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getPreparedSql() {
        return preparedSql;
    }

    public void setPreparedSql(String preparedSql) {
        this.preparedSql = preparedSql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
