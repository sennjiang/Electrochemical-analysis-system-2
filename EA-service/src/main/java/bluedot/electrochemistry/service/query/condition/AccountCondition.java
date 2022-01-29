package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.Table;
import bluedot.electrochemistry.service.query.searchable.Searchable;
import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Senn
 * @createDate 2021/12/15 17:08
 */
public class AccountCondition extends DefaultCondition {

    private static final Map<String,String> sqlMap = new HashMap<>();

    static {
        sqlMap.put("content"," content = ? ");
        sqlMap.put("status"," status = ? ");
        sqlMap.put("nearly"," DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(user.gmt_create) ");
    }

    private final Table table = Table.USER;

    @Param
    private Integer status;

    /**
     * 近几天 如 近三天 nearly = 3
     */
    @Param
    private Integer nearly;


    public AccountCondition(String content , Integer pageStart , Integer pageSize , Integer status) {
        this.content = content;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.status = status;
    }

    @Override
    public String decodeCondition() {
        if (checkCondition()) {
            StringBuilder sb = new StringBuilder("select * from user where");
            if (content != null) {
                sb.append(sqlMap.get("content"));
            }
            if (status != null) {
                sb.append(SQL_AND);
                sb.append(sqlMap.get("status"));
            }
            if (nearly != null) {
                sb.append(SQL_AND);
                sb.append(sqlMap.get("nearly"));
            }
            sb.append("limit ? , ?");
            return sb.toString();
        }
        return null;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNearly() {
        return nearly;
    }

    public void setNearly(Integer nearly) {
        this.nearly = nearly;
    }
}
