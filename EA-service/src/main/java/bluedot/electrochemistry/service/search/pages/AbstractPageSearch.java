package bluedot.electrochemistry.service.search.pages;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.factory.MapperFactory;
import bluedot.electrochemistry.pojo.domain.User;
import bluedot.electrochemistry.service.search.SearchResult;
import bluedot.electrochemistry.service.search.condition.Conditional;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sens
 * @createDate 2021/12/16 19:34
 */
abstract class AbstractPageSearch<E> implements PageSearchable<E>{

    @Autowired
    protected MapperFactory mapperFactory;

    @Override
    public SearchResult<E> search(Conditional condition) {
        String sql = condition.decodeCondition();
        BaseMapper mapper = new BaseMapper() {
            @Override
            public List<User> getAccountList(String condition) {
                ArrayList<User> list = new ArrayList<>();
                User user1 = new User();
                User user2 = new User();
                user1.setUsername("1");
                user2.setUsername("2");
                list.add(user1);
                list.add(user2);
                return list;
            }

            @Override
            public Integer getAccountCount(String condition) {
                return 100;
            }

            @Override
            public User loginByUsername(String account, String password) {
                return null;
            }

            @Override
            public User loginByEmail(String account, String password) {
                return null;
            }

            @Override
            public Integer checkEmail(String account) {
                return null;
            }

            @Override
            public Integer checkAccount(String account) {
                return null;
            }
        };
        List<E> list = getList(mapper, sql);
        Integer count = count(mapper, condition);
        return new SearchResult<>(count,list);
    }

    @Override
    public Integer count(BaseMapper mapper, Conditional condition) {
        return getCount(mapper ,condition.decodeCondition());
    }

    abstract Integer getCount(BaseMapper mapper, String condition);

    abstract List<E> getList(BaseMapper mapper, String condition);
}
