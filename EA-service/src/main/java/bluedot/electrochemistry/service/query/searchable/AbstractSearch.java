package bluedot.electrochemistry.service.query.searchable;

import bluedot.electrochemistry.service.dao.CommonMapper;
import bluedot.electrochemistry.service.factory.MapperFactory;
import bluedot.electrochemistry.service.pojo.domain.User;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.condition.Conditional;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sens
 * @createDate 2021/12/16 19:34
 */
abstract class AbstractSearch<T> implements Searchable<T> {

    @Autowired
    protected MapperFactory mapperFactory;

    @Override
    public SearchResult<T> search(Conditional condition) {
        String sql = condition.decodeCondition();
        CommonMapper mapper = new CommonMapper() {
            @Override
            public List<User> getAccountList(String condition) {
                ArrayList<User> list = new ArrayList<>();
                User user1 = new User();
                User user2 = new User();
//                user1.setName("1");
//                user2.setName("2");
                list.add(user1);
                list.add(user2);
                return list;
            }

            @Override
            public Integer getAccountCount(String condition) {
                return 100;
            }

            @Override
            public User getOneUser(String condition) {
                User user = new User();
//                user.setName("张三");
//                user.setAge(18);
                return user;
            }
        };

        if (condition.getSelectType() == SelectType.LIST) {
            List<T> list = getList(mapper, sql);
            Integer count = count(mapper, condition);
            return new SearchResult<>(count,list);
        }else if (condition.getSelectType() == SelectType.ONE){
            List<T> list = new ArrayList<>();
            T one = getOne(mapper, condition.decodeCondition());
            list.add(one);
            return new SearchResult<>(0,list);
        }else {
            return new SearchResult<>(count(mapper,condition),null);
        }
    }

    @Override
    public Integer count(CommonMapper mapper, Conditional condition) {
        return null;
    }

    List<T> getList(CommonMapper mapper, String condition) {
        return null;
    }

    T getOne(CommonMapper mapper, String condition){
        return null;
    }
}
