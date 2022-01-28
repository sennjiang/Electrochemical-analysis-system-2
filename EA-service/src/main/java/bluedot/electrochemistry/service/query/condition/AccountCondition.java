package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.Table;
import bluedot.electrochemistry.service.query.searchable.Searchable;
import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @createDate 2021/12/15 17:08
 */
public class AccountCondition extends DefaultCondition {

    private final Table table = Table.USER;

    @Param
    private final Integer status;

    public AccountCondition(String content , Integer pageStart , Integer pageSize , Integer status) {
        this.content = content;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.status = status;
    }

    @Override
    public String decodeCondition() {
        if (checkCondition()) {
            return "select * from user where name = ? status = ? limit ? , ?";
        }
        return null;
    }

    @Override
    public Object[] getQueryParams() {
        return new Object[0];
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
}
