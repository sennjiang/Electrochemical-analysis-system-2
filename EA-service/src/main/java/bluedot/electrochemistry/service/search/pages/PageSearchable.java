package bluedot.electrochemistry.service.search.pages;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.service.search.SearchResult;
import bluedot.electrochemistry.service.search.condition.Conditional;

import java.util.List;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface PageSearchable<E> {

    /**
     * 调用 mapper 做查询
     * @param condition 条件对象 用于生成条件语句
     */
    SearchResult<E> search(Conditional condition);

    /**
     *  调用 mapper 做查询
     * @param condition 条件
     * @return 数量
     */
    Integer count(BaseMapper mapper, Conditional condition);

}
