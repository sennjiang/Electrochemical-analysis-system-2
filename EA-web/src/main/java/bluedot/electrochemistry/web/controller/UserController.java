package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@WhiteMapping("/user")
public class UserController {

    @WhiteMapping("/login")
    public String login(String account, String password) {
        return "";
    }

    @WhiteMapping("/register")
    public String register(User user) {
        return "";
    }

    @WhiteMapping("/change/password")
    public String changePassword(String account, String password) {
        return "";
    }

    @WhiteMapping("/change/profile")
    public String changeInfo(User user) {
        return "";
    }

    @WhiteMapping("/verify/username")
    public String verifyUsername(String account) {
        return "";
    }

    @WhiteMapping("/verify/email")
    public String verifyEmail(String email) {
        return "";
    }

    @WhiteMapping("/add")
    public String addUser(User user) {
        return "";
    }

    @WhiteMapping("/send/email")
    public String sendEmail(String email) {
        return "";
    }

}
