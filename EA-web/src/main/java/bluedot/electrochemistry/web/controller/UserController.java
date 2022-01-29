package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.service.dao.BaseMapper;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.main.EditService;
import bluedot.electrochemistry.service.edit.EditType;
import bluedot.electrochemistry.service.factory.MapperFactory;
import bluedot.electrochemistry.service.pojo.domain.Right;
import bluedot.electrochemistry.service.pojo.domain.User;
import bluedot.electrochemistry.service.sender.MailSender;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.web.controller.base.BaseController;
import bluedot.electrochemistry.web.controller.base.Result;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    MapperFactory factory;

    @Autowired
    EditService editService;

    MailSender mailSender = new MailSender();


    private static final Logger LOGGER = LogUtil.getLogger(UserController.class);


    @WhiteMapping("/login")
    public Result login(@RequestParam("account") String account, @RequestParam("password") String password) {
        if (account == null) {
            return renderBadRequest();
        }
        BaseMapper mapper = factory.createMapper();
        User user = mapper.loginByEmail(account);
        if (user.getStatus() == 0) {
            return renderError("账号已冻结，请申请解冻！！");
        }
        if (!user.getPassword().equals(password)) {
            return renderError("账号密码错误！请重新登录");
        }
        user.setPassword("");
        return renderSuccess("登录成功！！",user);
    }

    @WhiteMapping("/register")
    public Result register(User user) {
        if (user == null || user.getEmail() == null) {
            return renderBadRequest();
        }
        String email = user.getEmail();
        BaseMapper mapper = factory.createMapper();
        Integer integer = mapper.checkEmail(email);
        if (integer == 0) {
            EditParam<User> param = new EditParam<>(new User[]{user}, EditType.INSERT);
            boolean flag = editService.doEdit(param);
            if (flag) return renderSuccess("注册成功！！");
        }
        return renderError("注册异常！！");
    }

    @WhiteMapping("/change/password")
    public Result changePassword(String id, String password) {

        if (id == null || password == null) return renderBadRequest();

        BaseMapper mapper = factory.createMapper();
        Integer integer = mapper.changePassword(id, password);
        if (integer == 1) return renderSuccess("修改密码成功！");
        return renderError("修改密码异常");
    }

    @RequestMapping("/change/profile")
    public Result changeInfo(User user) {
        if (user == null || user.getEmail() == null) {
            return renderBadRequest();
        }
        boolean flag = editService.doEdit(new EditParam<>(new User[]{user}, EditType.UPDATE));
        if (flag) {
            return renderSuccess();
        }
        return renderError("修改异常！！");
    }

    @WhiteMapping("/verify/email")
    public Result verifyEmail(String email) {
        if (email == null) return renderBadRequest();

        BaseMapper mapper = factory.createMapper();
        Integer integer = mapper.checkEmail(email);
        if (integer <= 0) return renderSuccess("账号不存在");
        return renderError("账号存在");
    }

    @RequestMapping("/add")
    public Result addUser(User user) {
        return null;
    }

    @WhiteMapping("/send/email/message")
    public Result sendEmail(String email,String message) throws MessagingException {
        if (email == null) {
            return renderBadRequest();
        }
        boolean b = mailSender.sendMessage(email, message);
        return b ? renderSuccess() : null;
    }

    @WhiteMapping("/freeze")
    public Result freeze(String email,String message) {
        //TODO freeze
        return renderBadRequest();
    }

    @WhiteMapping("/unfreeze")
    public Result unfreeze(String[] emails) {
        //TODO unfreeze
        return renderBadRequest();
    }

    @RequestMapping("/rights")
    public Result getRights(@RequestParam("id") String id) {
        if (id == null) return renderBadRequest();
        BaseMapper mapper = factory.createMapper();
        List<Right> rights = mapper.getRights(id);
        return renderSuccess("",rights);
    }


    /**
     * TODO 验证码 保存
     * @param email 邮箱
     * @return Result
     */
    @WhiteMapping("/send/email/code")
    public Result sendEmailCode(String email) throws MessagingException {
        if (email == null) {
            return renderBadRequest();
        }

        // 要发送的验证码
        String emailCode = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0, 5);

        boolean b = mailSender.sendMessage(email, "[电化学分析系统]您的验证码为:" + emailCode);
        return b ? renderSuccess() : null;
    }

    @RequestMapping("/upload/avatar")
    public Result uploadAvatar(MultipartFile file) {
        return renderBadRequest();
    }

    /**
     * 判断是否是邮箱
     * @param email 邮箱
     * @return 是否
     */
    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
