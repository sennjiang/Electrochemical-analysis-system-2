package bluedot.electrochemistry.dao;

import bluedot.electrochemistry.pojo.domain.User;

/**
 * @author Senn
 * @create 2022/1/20 19:51
 */
public interface CommonMapper {

    Integer checkEmail(String account);

    User loginByEmail(String account, String password);

}
