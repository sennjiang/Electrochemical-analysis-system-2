package bluedot.electrochemistry.service.search.main;

import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.search.SearchDirection;
import bluedot.electrochemistry.service.search.SearchPage;
import bluedot.electrochemistry.service.search.SearchResult;
import bluedot.electrochemistry.service.search.condition.Conditional;

import java.util.List;

/**
 * @author Senn
 * @create 2021/12/26 17:00
 */
public class SearchService implements SearchModularity{

    SearchDirection direction;

    @Override
    public SearchResult<?> doService(Conditional condition, SearchPage page) throws IllegalIndexException {
        return direction.get(page.getIndex()).search(condition);
    }

    @Override
    public void init() {
        direction = new SearchDirection();
        direction.init();
    }

    @Override
    public void destroy() {

    }


}
