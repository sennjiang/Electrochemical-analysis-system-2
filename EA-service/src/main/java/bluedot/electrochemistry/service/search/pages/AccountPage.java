package bluedot.electrochemistry.service.search.pages;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.simplespring.core.annotation.Component;

import java.util.List;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
@Component
public class AccountPage extends AbstractPageSearch<User>{

    @Override
    List<User> getList(BaseMapper mapper, String condition) {
        return mapper.getAccountList(condition);
    }
}
