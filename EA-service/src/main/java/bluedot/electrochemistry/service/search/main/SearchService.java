package bluedot.electrochemistry.service.search.main;

import bluedot.electrochemistry.service.Lifecycle;
import bluedot.electrochemistry.exception.IllegalIndexException;
import bluedot.electrochemistry.service.search.SearchResult;
import bluedot.electrochemistry.service.search.condition.Conditional;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface SearchService extends Lifecycle {

    SearchResult<?> doService(Conditional condition) throws IllegalIndexException;
}