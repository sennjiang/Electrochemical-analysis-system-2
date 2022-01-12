package bluedot.electrochemistry.service.search.main;

import bluedot.electrochemistry.service.Lifecycle;
import bluedot.electrochemistry.service.search.condition.Conditional;
import bluedot.electrochemistry.service.search.pages.PageSearchable;

import java.util.List;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface SearchModularity extends Lifecycle {

    List<?> doService(Conditional condition, PageSearchable<?> pageSearchable);
}