package bluedot.electrochemistry.factory;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.simplemybatis.session.SqlSession;
import bluedot.electrochemistry.simplemybatis.session.SqlSessionFactory;
import bluedot.electrochemistry.simplespring.core.annotation.Repository;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * 由于所有的查询都使用同一个的查询接口，所以这里创建创建查询代理对象的工厂
 * @author xxbb
 */
@Repository
public class MapperFactory {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    public BaseMapper createMapper(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.setAutoCommit(true);
        return sqlSession.getMapper(BaseMapper.class);
    }
}
