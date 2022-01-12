package bluedot.electrochemistry.service.search.condition;

/**
 * @author Sens
 * @createDate 2021/12/15 17:08
 */
public class AccountCondition extends DefaultCondition {

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
            return "name = \" " + content + " \" " + SQL_AND + " status = " + status + " "+ SQL_LIMIT + " " + ( pageStart + 1 ) * pageSize + " " + pageSize;
        }
        return null;
    }
}
