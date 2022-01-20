package bluedot.electrochemistry.service.query.main;

import bluedot.electrochemistry.factory.MapperFactory;
import bluedot.electrochemistry.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.condition.Conditional;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * @author Senn
 * @create 2022/1/13 14:11
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    MapperFactory mapperFactory;

    @Override
    public void init() {
    }

    @Override
    public void destroy() {

    }

    @Override
    public SearchResult<?> doService(Conditional condition) throws IllegalIndexException {
        return condition.getSearchable().search(condition);
    }
}
