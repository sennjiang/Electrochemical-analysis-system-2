package bluedot.electrochemistry.service.search.main;

import bluedot.electrochemistry.factory.MapperFactory;
import bluedot.electrochemistry.exception.IllegalIndexException;
import bluedot.electrochemistry.service.search.SearchDirection;
import bluedot.electrochemistry.service.search.SearchResult;
import bluedot.electrochemistry.service.search.condition.Conditional;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * @author Senn
 * @create 2022/1/13 14:11
 */
@Service
public class SearchServiceImpl implements SearchService{

    static SearchDirection direction;

    @Autowired
    MapperFactory mapperFactory;

    @Override
    public void init() {
        direction = new SearchDirection();
        direction.init();
    }

    @Override
    public void destroy() {

    }

    @Override
    public SearchResult<?> doService(Conditional condition) throws IllegalIndexException {
        return direction.get(condition.getPage().getIndex()).search(condition);
    }
}
