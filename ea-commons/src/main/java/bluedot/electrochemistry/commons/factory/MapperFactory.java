package bluedot.electrochemistry.commons.factory;

import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.dao.BaseMapper;
import bluedot.electrochemistry.commons.dao.FilterMapper;
import bluedot.electrochemistry.simplemybatis.session.SqlSession;
import bluedot.electrochemistry.simplemybatis.session.SqlSessionFactory;
import bluedot.electrochemistry.simplemybatis.utils.LogUtils;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import org.slf4j.Logger;

/**
 * 由于所有的查询都使用同一个的查询接口，所以这里创建创建查询代理对象的工厂
 * @author Senn
 */
public class MapperFactory implements Lifecycle {

    private static SqlSessionFactory sqlSessionFactory;

    private static final Logger LOGGER = LogUtils.getLogger();
    
    public BaseMapper createMapper(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.setAutoCommit(true);
        return sqlSession.getMapper(BaseMapper.class);
    }
    public FilterMapper createFilterMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.setAutoCommit(true);
        return sqlSession.getMapper(FilterMapper.class);
    }

    @Override
    public void init() {
        sqlSessionFactory = (SqlSessionFactory) BeanContainer.getInstance().getBean(SqlSessionFactory.class);
        LOGGER.debug("load bean: " + MapperFactory.class.getName());
        MapperFactory factory = new MapperFactory();
        BeanContainer.getInstance().addBean(MapperFactory.class, factory);
    }

    @Override
    public void destroy() {

    }
}
