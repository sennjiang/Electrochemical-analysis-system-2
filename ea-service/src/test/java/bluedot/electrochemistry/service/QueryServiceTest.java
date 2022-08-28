package bluedot.electrochemistry.service;

import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.condition.AccountCondition;
import bluedot.electrochemistry.service.query.main.QueryService;
import bluedot.electrochemistry.service.query.main.QueryServiceImpl;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Senn
 * @create 2022/1/12 21:13
 */
public class QueryServiceTest {

    @Test
    public void test() throws IllegalIndexException {
        QueryService service = new QueryServiceImpl();
        AccountCondition accountCondition = new AccountCondition();
        accountCondition.setNearly("3");
        accountCondition.setContent("sen");
        accountCondition.setStatus("1");
        accountCondition.setPageStart(20);
        accountCondition.setPageSize(10);
        accountCondition.setType(SelectType.LIST);
        SearchResult<?> list = service.doService(accountCondition);
        Assertions.assertNotNull(list);
    }
}
