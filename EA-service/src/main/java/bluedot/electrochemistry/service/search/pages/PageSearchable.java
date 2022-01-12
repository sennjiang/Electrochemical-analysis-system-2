package bluedot.electrochemistry.service.search.pages;

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
    List<E> search(Conditional condition);

}
