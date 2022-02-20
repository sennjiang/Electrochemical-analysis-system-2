package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.cache.Cacheable;
import bluedot.electrochemistry.cache.local.StringArrayCache;
import bluedot.electrochemistry.commons.dao.BaseMapper;
import bluedot.electrochemistry.commons.entity.Right;
import bluedot.electrochemistry.commons.entity.User;
import bluedot.electrochemistry.commons.factory.CacheExecutorFactory;
import bluedot.electrochemistry.commons.VerifyCodeMaker;
import bluedot.electrochemistry.commons.factory.SenderProcessorFactory;
import bluedot.electrochemistry.commons.sender.handler.Message;
import bluedot.electrochemistry.commons.sender.handler.SendType;
import bluedot.electrochemistry.commons.sender.handler.SenderHandler;
import bluedot.electrochemistry.commons.sender.processor.SenderProcessor;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditType;
import bluedot.electrochemistry.service.edit.main.EditService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.web.controller.base.BaseController;
import bluedot.electrochemistry.commons.factory.MapperFactory;
import bluedot.electrochemistry.web.controller.base.Result;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Senn
 * @create 2021/12/26 11:28
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    StringArrayCache arrayCache = CacheExecutorFactory.createStringArrayCache();

    Cacheable<String, String> cacheable = CacheExecutorFactory.createCodeCache();

//    @Autowired TODO open
    SenderProcessor senderProcessor = SenderProcessorFactory.createSenderProcessor();

    @Autowired
    MapperFactory factory;

    @Autowired
    EditService editService;

    private static final Logger LOGGER = LogUtil.getLogger(UserController.class);


    @WhiteMapping("/login")
    public Result login(@RequestParam("account") String account, @RequestParam("password") String password) {
        if (account == null || password == null) {
            return renderBadRequest();
        }
        BaseMapper mapper = factory.createMapper();
        User user = mapper.loginByEmail(account);
        if (!user.getPassword().equals(password)) {
            return renderError("账号密码错误！请重新登录");
        }
        if (user.getStatus() == 0) {
            return renderError("账号已冻结，请申请解冻！！");
        }
        List<String> roles = mapper.getRolesById(account);
        arrayCache.put(String.valueOf(user.getId()), roles.toArray(new String[0]));
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
        return renderSuccess();
    }

    @WhiteMapping("/freeze")
    public Result freeze(String email,String message) {
        //TODO freeze
        return renderSuccess();
    }

    @WhiteMapping("/unfreeze")
    public Result unfreeze(String[] emails) {
        //TODO unfreeze
        return renderSuccess();
    }

    @RequestMapping("/rights")
    public Result getRights(@RequestParam("id") String id) {
        if (id == null) return renderBadRequest();
        BaseMapper mapper = factory.createMapper();
        List<Right> rights = mapper.getRights(id);
        return renderSuccess("",rights);
    }


    /**
     * TODO 验证码 保存 Redis
     * @param email 邮箱
     * @return Result
     */
    @WhiteMapping("/send/email/code")
    public Result sendEmailCode(@RequestParam("email") String email) throws MessagingException {
        if (email == null || !isEmail(email)) {
            return renderBadRequest();
        }
        String emailCode = VerifyCodeMaker.getVerifyCode();
        cacheable.put(email, emailCode);
        boolean b =  senderProcessor.sender(new Message(email,emailCode, SendType.VERIFY_CODE));
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
    private static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
