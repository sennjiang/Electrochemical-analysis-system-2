package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.service.dao.BaseMapper;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditService;
import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.factory.MapperFactory;
import bluedot.electrochemistry.service.pojo.domain.User;
import bluedot.electrochemistry.service.query.condition.AccountCondition;
import bluedot.electrochemistry.service.query.main.QueryService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import org.slf4j.Logger;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MapperFactory factory;

    @Autowired
    QueryService queryService;

    @Autowired
    EditService editService;


    private static final Logger LOGGER = LogUtil.getLogger(UserController.class);


    @RequestMapping("/login")
    public User login(@RequestParam("account") String account, @RequestParam("password") String password) {
        BaseMapper mapper = factory.createMapper();
        User user = mapper.loginByEmail(account, password);
        return user;
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
