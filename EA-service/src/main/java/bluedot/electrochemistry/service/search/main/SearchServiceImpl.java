package bluedot.electrochemistry.service.search.main;

import bluedot.electrochemistry.factory.MapperFactory;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
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
        return null;
    }
}
