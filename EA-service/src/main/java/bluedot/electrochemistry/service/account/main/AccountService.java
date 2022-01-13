package bluedot.electrochemistry.service.account.main;

import bluedot.electrochemistry.service.account.VerifyResult;
import bluedot.electrochemistry.service.account.VerifyTask;

/**
 * @author Senn
 */
public interface AccountService {
    VerifyResult doService(VerifyTask task);
}
