package bluedot.electrochemistry.service.account.verify;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.pojo.domain.User;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public class AccountVerify implements Verify {

    @Override
    public User verifyAccount(BaseMapper mapper, String account, String password) {
        return mapper.loginByUsername(account,password);
    }

    @Override
    public boolean checkAccount(BaseMapper mapper, String account) {
        return mapper.checkAccount(account) == 1;
    }
}
