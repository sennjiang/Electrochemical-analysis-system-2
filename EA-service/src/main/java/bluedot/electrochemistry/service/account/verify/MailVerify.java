package bluedot.electrochemistry.service.account.verify;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public class MailVerify implements Verify{

    @Override
    public boolean verifyAccount(String account) {
        //TODO
        System.out.println("verifyAccount : " + account);
        return true;
    }
}