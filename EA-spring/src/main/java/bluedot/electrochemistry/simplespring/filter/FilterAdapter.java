package bluedot.electrochemistry.simplespring.filter;

import bluedot.electrochemistry.common.ClassUtil;
import bluedot.electrochemistry.common.LogUtil;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Senn
 * @create 2022/1/22 18:48
 */
public class FilterAdapter {

    private static final Logger LOGGER = LogUtil.getLogger("spring.filter");

    private final Set<FilterDefinition> beforeFilters = new TreeSet<>();

    private final Set<FilterDefinition> afterFilters = new TreeSet<>();


    public void doBeforeFilter(HttpServletRequest request, HttpServletResponse response) {
        for (FilterDefinition next : beforeFilters) {
            SpringFilter filter = next.getFilter();
            LOGGER.debug("do before filter filter name :  " + filter.getClass().getName());
            if (!filter.beforeFilter(request, response)) {
                throw new RuntimeException("do before filter fail ");
            }
        }
    }

    public void doAfterFilter(HttpServletRequest request, HttpServletResponse response, Object returnValue) {
        for (FilterDefinition next : afterFilters) {
            SpringFilter filter = next.getFilter();
            LOGGER.debug("do after filter filter name :  " + filter.getClass().getName());
            if (!filter.afterFilter(request, response,returnValue)) {
                throw new RuntimeException("do after filter fail ");
            }
        }
    }

    public void addBeforeFilter(Class<?> clazz, int level) {
        SpringFilter filter =  (SpringFilter) ClassUtil.newInstance(clazz, true);
        FilterDefinition filterDefinition = new FilterDefinition(filter, level);
        beforeFilters.add(filterDefinition);
    }

    public void addAfterFilter(Class<?> clazz, int level) {
        SpringFilter filter =  (SpringFilter) ClassUtil.newInstance(clazz, true);
        FilterDefinition filterDefinition = new FilterDefinition(filter, level);
        afterFilters.add(filterDefinition);
    }

    /**
     * 是否需要 前置过滤器
     * @return 是 否
     */
    public boolean needDoBefore() {
        return beforeFilters.size() > 0;
    }

    /**
     * 是否需要 前置过滤器
     * @return 是 否
     */
    public boolean needDoAfter() {
        return afterFilters.size() > 0;
    }

    public void getFilters() {
        beforeFilters.forEach(System.out::println);
        afterFilters.forEach(System.out::println);
    }
}
