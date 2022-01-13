package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(String account, String password) {
        return "";
    }

    @RequestMapping("/register")
    public String register(User user) {
        return "";
    }

    @RequestMapping("/change/password")
    public String changePassword(String account, String password) {
        return "";
    }

    @RequestMapping("/change/profile")
    public String changeInfo(User user) {
        return "";
    }

    @RequestMapping("/verify/username")
    public String verifyUsername(String account) {
        return "";
    }

    @RequestMapping("/verify/email")
    public String verifyEmail(String email) {
        return "";
    }

    @RequestMapping("/add")
    public String addUser(User user) {
        return "";
    }

    @RequestMapping("/send/email")
    public String sendEmail(String email) {
        return "";
    }

}
