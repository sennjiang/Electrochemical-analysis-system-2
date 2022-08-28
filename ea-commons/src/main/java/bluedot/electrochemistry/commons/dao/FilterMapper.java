package bluedot.electrochemistry.commons.dao;

import java.util.List;

/**
 * @author Senn
 * @create 2022/3/29 22:05
 */
public interface FilterMapper {

    Integer getUserMaxId();

    List<Integer> getUserIds(int pageStart, int pageSize);
}

