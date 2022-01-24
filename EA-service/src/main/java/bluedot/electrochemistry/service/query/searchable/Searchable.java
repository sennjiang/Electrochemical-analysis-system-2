package bluedot.electrochemistry.service.query.searchable;

import bluedot.electrochemistry.service.dao.BaseMapper;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.condition.Conditional;

/**
 * @author Senn
 * @createDate 2022/1/20 19:17
 */
public interface Searchable<T> {
    /**
     * 调用 mapper 做查询
     * @param condition 条件对象 用于生成条件语句
     */
    SearchResult<T> search(Conditional condition);

    /**
     *  调用 mapper 做查询
     * @param condition 条件
     * @return 数量
     */
    Integer count(BaseMapper mapper, Conditional condition);
}
