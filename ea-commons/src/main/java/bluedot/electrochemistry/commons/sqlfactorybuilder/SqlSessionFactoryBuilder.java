package bluedot.electrochemistry.commons.sqlfactorybuilder;

import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.factory.MapperFactory;
import bluedot.electrochemistry.simplemybatis.session.Configuration;
import bluedot.electrochemistry.simplemybatis.session.SqlSessionFactory;
import bluedot.electrochemistry.simplemybatis.session.defaults.DefaultSqlSessionFactory;
import bluedot.electrochemistry.simplemybatis.utils.LogUtils;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * 构建者模式创建sqlSession工厂对象
 *
 * @author Senn
 * @create 2022/1/24 19:37
 */
public class SqlSessionFactoryBuilder implements Lifecycle {

    private static final Logger LOGGER = LogUtils.getLogger();
    /**
     * 读取配置文件构建SqlSessionFactory工厂
     * 将配置文件的解析成输入流的工作也放到该方法中
     *
     * @param fileName 配置文件名
     * @return 工厂对象
     */
    public SqlSessionFactory build(String fileName) {

        InputStream is = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            LOGGER.warn("there is no  mapper.location!! mapper name : " + fileName);
        }
        return build(is);
    }

    public SqlSessionFactory build(InputStream inputStream) {
        try {
            Configuration.pros.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = DefaultSqlSessionFactory.getInstance(new Configuration());
        //将sqlFactory注入到IoC容器中
        LOGGER.debug("load bean: " + SqlSessionFactory.class.getName());
        BeanContainer.getInstance().addBean(SqlSessionFactory.class, factory);
        init();
        return factory;
    }

    @Override
    public void init() {
        new MapperFactory().init();
    }

    @Override
    public void destroy() {

    }
}
