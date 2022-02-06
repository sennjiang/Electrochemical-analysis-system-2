package bluedot.electrochemistry.service.query.main;

import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.condition.Conditional;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface QueryService {
    SearchResult<?> doService(Conditional condition) throws IllegalIndexException;
}