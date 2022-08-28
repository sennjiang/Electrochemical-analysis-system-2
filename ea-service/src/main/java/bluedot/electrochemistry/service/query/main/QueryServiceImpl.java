package bluedot.electrochemistry.service.query.main;

import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.QueryParam;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.commons.table.Table;
import bluedot.electrochemistry.service.query.condition.Conditional;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import org.slf4j.Logger;

import java.util.ArrayList;

/**
 * @author Senn
 * @create 2022/1/13 14:11
 */
@Service
public class QueryServiceImpl implements QueryService {

    private static final Logger LOGGER = LogUtil.getLogger(QueryServiceImpl.class);

    @Override
    public SearchResult<?> doService(Conditional condition) throws IllegalIndexException {
        String preparedSql = condition.decodeCondition();
        Table table = condition.getTable();
        Object[] queryParams = condition.getQueryParams();
        SelectType selectType = condition.getSelectType();
        QueryParam param = new QueryParam(selectType,table,preparedSql,queryParams);
        LOGGER.info("preparedSql : {}",preparedSql);
        for (Object queryParam : queryParams) {
            LOGGER.info("queryParam : {}",queryParam);
        }
        LOGGER.info("SelectType : {}",selectType);

        return new SelectExecutor().doSelect(param);
    }

    private static class SelectExecutor {
        public SearchResult<?> doSelect(QueryParam param) {
            return new SearchResult<>(10,new ArrayList<>());
        }
    }
}
