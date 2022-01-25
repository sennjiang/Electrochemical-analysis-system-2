package bluedot.electrochemistry.service.dao;

import bluedot.electrochemistry.service.pojo.domain.User;

import java.util.List;

/**
 * @author Senn
 * @create 2022/1/20 19:51
 */
public interface CommonMapper {

    /**
     * 获取列表
     * @param condition 条件
     * @return 结果集合
     */
    List<User> getAccountList(String condition);

    /**
     * 获取Account 总数量
     * @param condition 条件
     * @return 数量
     */
    Integer getAccountCount(String condition);


    User getOneUser(String condition);

}
