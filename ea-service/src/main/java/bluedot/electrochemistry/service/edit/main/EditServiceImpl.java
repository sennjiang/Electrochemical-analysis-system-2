package bluedot.electrochemistry.service.edit.main;

import bluedot.electrochemistry.service.edit.EditMultiType;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditType;
import bluedot.electrochemistry.simplemybatis.session.SqlSession;
import bluedot.electrochemistry.simplemybatis.session.SqlSessionFactory;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

import java.util.Objects;

/**
 * @author Senn
 * @create 2022/1/13 13:52
 */
@Service
public class EditServiceImpl implements EditService{

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean doEdit(EditParam<?> param) {
        if (Objects.isNull(param) || param.isNull()) return false;
        if (param.getMultiType() == EditMultiType.SINGLE) {
            return doSingleEdit(param);
        }else if (param.getMultiType() == EditMultiType.MULTITUDE) {
            return doMultiEdit(param);
        }
        return false;
    }

    private boolean doMultiEdit(EditParam<?> param) {
        boolean flag = false;
        if (EditType.INSERT == param.getType()){
            flag = batchInsert(param.getTs());
        }
        if (EditType.DELETE == param.getType()){
            flag = batchDelete(param.getTs()) ;
        }
        if (EditType.UPDATE == param.getType()){
            flag = batchUpdate(param.getTs()) ;
        }
        return flag;
    }

    private boolean doSingleEdit(EditParam<?> param) {
        boolean flag = false;
        if (EditType.INSERT == param.getType()){
            flag = insert(param.getTs()) >= 1;
        }
        if (EditType.DELETE == param.getType()){
            flag =delete(param.getTs()) >= 1;
        }
        if (EditType.UPDATE == param.getType()){
            flag = update(param.getTs()) >= 1;
        }
        return flag;
    }

    /**
     * 注意：默认的session关闭了自动提交功能
     *
     * @return session
     */
    private SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    private SqlSession getAutoCommitSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.setAutoCommit(true);
        return sqlSession;
    }

    private <T> int insert(T type) {
        return insert(type,getAutoCommitSession());
    }

    /**
     * 插入记录
     * @param type  泛型 实体对象
     * @param sqlSession 数据库连接
     * @param <T> 泛型方法
     * @return 影响条数
     */
    private <T> int insert(T type, SqlSession sqlSession) {
        return sqlSession.insert(type);
    }

    /**
     * 批量更新 默认开启事务
     * @param ts T[]
     * @return 成功 返回 true,失败返回 false
     */
    private <T> boolean batchInsert(T[] ts) {
        SqlSession session = getSession();
        int res = 0;
        for (T t : ts) {
            insert(t,session);
        }
        boolean success = res == ts.length;
        if (success)
            session.commit();
        if (!success)
            session.rollback();
        return success;
    }


    private  <T> int update(T type) {
        SqlSession session = getAutoCommitSession();
        return delete(type,session);
    }

    /**
     * 更新记录
     * @param type  泛型 实体对象
     * @param sqlSession 数据库连接
     * @param <T> 泛型方法
     * @return 影响条数
     */
    private <T> int update(T type, SqlSession sqlSession) {
        return sqlSession.update(type);
    }

    /**
     * 批量更新 默认开启事务
     * @param ts T[]
     * @return 成功 返回 true,失败返回 false
     */
    public <T> boolean batchUpdate(T[] ts) {
        SqlSession session = getSession();
        int res = 0;
        for (T t : ts) {
            update(t,session);
        }
        boolean success = res == ts.length;
        if (success)
            session.commit();
        if (!success)
            session.rollback();
        return success;
    }

    public <T> int delete(T type) {
        return delete(type,getAutoCommitSession());
    }

    /**
     * 删除记录
     * @param type  泛型 实体对象
     * @param sqlSession 数据库连接
     * @param <T> 泛型方法
     * @return 影响条数
     */
    private <T> int delete(T type, SqlSession sqlSession) {
        return sqlSession.delete(type);
    }


    /**
     * 批量删除 默认开启事务
     * @param ts T[]
     * @return 成功 返回 true,失败返回 false
     */
    public <T> boolean batchDelete(T[] ts) {
        SqlSession session = getSession();
        int res = 0;
        for (T t : ts) {
            res += delete(t,session);
        }
        boolean success = res == ts.length;
        if (success)
            session.commit();
        if (!success)
            session.rollback();
        return success;
    }
}
