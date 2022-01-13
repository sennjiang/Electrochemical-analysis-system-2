package bluedot.electrochemistry.service.account.main;

import bluedot.electrochemistry.factory.MapperFactory;
import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.service.account.VerifyResult;
import bluedot.electrochemistry.service.account.VerifyTask;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * @author Senn
 * @create 2022/1/13 14:13
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    MapperFactory mapperFactory;

    @Override
    public VerifyResult doService(VerifyTask task) {
        VerifyResult result = new VerifyResult();
        if (VerifyTask.CHECK_ACCOUNT.equals(task.getType())) {
            boolean flag = task.checkAccount(mapperFactory.createMapper());
            result.setType(VerifyResult.CHECK_ACCOUNT);
            if (flag) result.setFlag(true);
        }
        if (VerifyTask.LOGIN.equals(task.getType())) {
            User user = task.login(mapperFactory.createMapper());
            result.setType(VerifyResult.LOGIN);
            result.setUser(user);
        }
        return result;
    }
}
