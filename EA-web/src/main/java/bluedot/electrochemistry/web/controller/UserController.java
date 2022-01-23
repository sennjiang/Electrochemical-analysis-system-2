package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/test")
    public String test() {
        logger.info(" /user/test 请求成功！！！");
        return "hello world !! ";
    }

    @RequestMapping("/user")
    public String test2(User user) {
        logger.info(" /user/user 请求成功！！！user : " + user);
        return JsonUtil.toJson(user);
    }

    @RequestMapping("/t")
    public String test1(@RequestParam("name") String name) {
        logger.info(" /user/t 请求成功！！！ name : " + name);
        return name;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        return "hello world !! ";
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
