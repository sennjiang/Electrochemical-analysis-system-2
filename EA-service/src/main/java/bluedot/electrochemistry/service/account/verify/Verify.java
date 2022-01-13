package bluedot.electrochemistry.service.account.verify;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.pojo.domain.User;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface Verify {

    User verifyAccount(BaseMapper mapper, String account, String password);

    boolean checkAccount(BaseMapper mapper, String account);
}
