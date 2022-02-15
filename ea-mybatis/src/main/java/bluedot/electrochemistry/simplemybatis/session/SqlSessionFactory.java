package bluedot.electrochemistry.simplemybatis.session;

/**
 * sqlSession会话工厂
 *
 * @Author zero
 * @Create 2022/2/10 14:50
 */
public interface SqlSessionFactory {
    /**
     * 创建session对象，开始数据库会话
     *
     * @return 数据库会话对象
     */
    SqlSession openSession();
}
