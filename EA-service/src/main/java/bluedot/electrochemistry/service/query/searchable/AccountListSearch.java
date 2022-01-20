package bluedot.electrochemistry.service.query.searchable;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.service.query.condition.Conditional;

import java.util.List;

/**
 * @author Senn
 * @create 2022/1/20 19:37
 */
public class AccountListSearch extends AbstractSearch<User>{

    @Override
    List<User> getList(BaseMapper mapper, String condition) {
        return mapper.getAccountList(condition);
    }

    @Override
    public Integer count(BaseMapper mapper, Conditional condition) {
        return mapper.getAccountCount(condition.decodeCondition());
    }
}
