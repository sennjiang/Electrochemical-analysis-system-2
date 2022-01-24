package bluedot.electrochemistry.service.dao;

import bluedot.electrochemistry.service.pojo.domain.User;

/**
 * @author Senn
 * @create 2022/1/20 19:51
 */
public interface CommonMapper {

    Integer checkEmail(String account);

    User loginByEmail(String account, String password);

}
