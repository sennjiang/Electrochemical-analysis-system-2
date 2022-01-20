package bluedot.electrochemistry.service.query.condition;

import bluedot.electrochemistry.service.query.SearchType;
import bluedot.electrochemistry.service.query.searchable.Searchable;

/**
 * @author Senn
 * @createDate 2021/12/15 17:08
 */
public class AccountCondition extends DefaultCondition {

    private SearchType type;

    private Searchable<?> search;

    private Integer status;

    public AccountCondition(String content , Integer pageStart , Integer pageSize , Integer status) {
        this.content = content;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.status = status;
    }

    @Override
    public String decodeCondition() {
        if (checkCondition()) {
            return "name = \"" + content + "\" " + SQL_AND + " status = " + status + " "+ SQL_LIMIT + " " + ( pageStart + 1 ) * pageSize + " " + pageSize;
        }
        return null;
    }

    public void setSearchable(Searchable<?> search) {
        this.search = search;
    }

    public void setType(SearchType type) {
        this.type = type;
    }

    @Override
    public SearchType getType() {
        return this.type;
    }

    @Override
    public Searchable<?> getSearchable() {
        return this.search;
    }
}
