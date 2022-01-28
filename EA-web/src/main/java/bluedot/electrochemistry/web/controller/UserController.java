package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditService;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.pojo.domain.User;
import bluedot.electrochemistry.service.query.condition.AccountCondition;
import bluedot.electrochemistry.service.query.main.QueryService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    QueryService queryService;

    @Autowired
    EditService editService;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @WhiteMapping("/user")
    public String test2(AccountCondition condition) throws IllegalIndexException {
        return queryService.doService(condition).toString();
    }


    @RequestMapping("/file/test")
    public String test(MultipartFile[] files) throws IOException {
        LOGGER.info(" /user/file/test 请求成功！！！");
        for (MultipartFile file : files) {
            LOGGER.info("{}", file.getName());
            LOGGER.info("{}",file.getSize());
            LOGGER.info("{}",file.isEmpty());
            File file1 = new File("E:\\testImage", "qwer.jpg");
            file.transferTo(file1);
            LOGGER.info(" --------- ");
        }
        return "hello world !! ";
    }


    @RequestMapping("/t")
    public String test1(String[] ids) {
        LOGGER.info(" /user/t 请求成功！！！ name : " + ids);
        return ids[0];
    }

    @RequestMapping("/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        return "hello world !! ";
    }

    @WhiteMapping("/register")
    public String register(User user) {
        EditParam<User> param = new EditParam<>(user,EditParam.UPDATE);
        boolean flag = editService.doEdit(param);
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
