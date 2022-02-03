package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.Table;
import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Senn
 * @createDate 2021/12/15 17:08
 */
public class AccountCondition extends DefaultCondition {

    private static final Map<Integer,String> sqlMap = new HashMap<>();

    private static final String SQL_LIMIT = "limit ? , ? ;";

    private static final String SQL_SELECT = "select * from user ";



    static {
        sqlMap.put(1, "where content = ? ");
        sqlMap.put(2, "where status = ? ");
        sqlMap.put(4, "DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(user.gmt_create) ");
        sqlMap.put(3, "where content = ? and status = ?");
        sqlMap.put(5, "where content = ? and DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(user.gmt_create)");
        sqlMap.put(6, "where status = ? and DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(user.gmt_create)");
        sqlMap.put(7, "where content = ? and status = ? and DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(user.gmt_create) ");

    }

    private final Table table = Table.USER;

    @Param
    private String status;

    /**
     * 近几天 如 近三天 nearly = 3
     */
    @Param
    private String nearly;

    /**
     * 标记属性
     */
    private int tag = 0;

    @Override
    public String decodeCondition() {
        if (tag == 0) return SQL_SELECT + SQL_LIMIT;
        return SQL_SELECT + sqlMap.getOrDefault(tag, null) + SQL_LIMIT;
    }

    @Override
    public Object[] getQueryParams() {
        ArrayList<Object> objects = new ArrayList<>();
        if (content != null) {
            objects.add(content);
        }
        if (status != null) {
            objects.add(status);
        }
        if (nearly != null) {
            objects.add(nearly);
        }
        objects.add((pageStart - 1) * pageSize);
        objects.add(pageSize);
        return objects.toArray(new Object[0]);
    }

    public void setType(SelectType type) {
        this.type = type;
    }

    @Override
    public SelectType getSelectType() {
        return this.type;
    }

    @Override
    public Table getTable() {
        return table;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (this.status == null) {
            this.tag += 2;
        }
        this.status = status;
    }

    public String getNearly() {
        return nearly;
    }

    public void setNearly(String nearly) {
        if (this.nearly == null) {
            this.tag += 4;
        }
        this.nearly = nearly;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (this.content == null) {
            this.tag += 1;
        }
        this.content = content;
    }
}
