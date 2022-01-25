package bluedot.electrochemistry.web.core;

import bluedot.electrochemistry.simplemybatis.session.SqlSessionFactory;
import bluedot.electrochemistry.simplemybatis.session.defaults.DefaultSqlSessionFactory;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.SpringConstant;
import bluedot.electrochemistry.simplespring.core.annotation.Bean;
import bluedot.electrochemistry.simplespring.core.annotation.Configuration;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.inject.DependencyInject;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.processor.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.DoRequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.DoFileProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.PreRequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.StaticResourceRequestProcessor;
import bluedot.electrochemistry.simplespring.util.ClassUtil;
import bluedot.electrochemistry.simplespring.util.ValidationUtil;
import bluedot.electrochemistry.web.sqlfactorybuilder.SqlSessionFactoryBuilder;
import bluedot.electrochemistry.web.util.LogUtil;
import org.slf4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author Senn
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "contextConfigLocation", value = "application.properties")},
        loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LogUtil.getLogger(DispatcherServlet.class);

    /**
     * 保存application.properties配置文件中的内容
     */
    private Properties contextConfig = new Properties();

    /**
     * 请求处理器
     */
    List<RequestProcessor> PROCESSORS = new ArrayList<>();

    @Override
    public void init(ServletConfig servletConfig) {
        LOGGER.info("ready init in dispatcherServlet");



        //读取配置文件，保存属性到contextConfig
        doLoadConfig(servletConfig.getInitParameter("contextConfigLocation"));

        //初始化容器
        BeanContainer beanContainer = BeanContainer.getInstance();

        loadBeans(contextConfig.getProperty("spring.controllerPackage"));

        loadBeans(contextConfig.getProperty("spring.scanPackage"));
        //AOP织入
//        new AspectWeaver().doAspectOrientedProgramming();
        //初始化简易mybatis框架，往IoC容器中注入SqlSessionFactory对象
        //TODO 初始化mybatis异常
//        new SqlSessionFactoryBuilder().build(servletConfig.getInitParameter("contextConfigLocation"));

        beanContainer.addBean(DefaultSqlSessionFactory.class,new DefaultSqlSessionFactory());

        new DependencyInject().doDependencyInject();

        //初始化请求处理器责任链
        // 预处理的请求处理器
        PROCESSORS.add(new PreRequestProcessor());

        // 静态资源的请求处理器（如果是静态资源让RequestDispatcher自己处理）
        PROCESSORS.add(new StaticResourceRequestProcessor(servletConfig.getServletContext()));

        PROCESSORS.add(new DoRequestProcessor());

        PROCESSORS.add(new DoFileProcessor());



    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        LOGGER.info("ready service in dispatcherServlet");

        //1.创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSORS.iterator(), request, response);
        //2.通过责任链模式来一次调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        //3.对处理结果进行渲染
        requestProcessorChain.doRender();
    }

    /**
     * 加载配置文件
     *
     * @param contextConfigLocation properties配置文件
     */
    private void doLoadConfig(String contextConfigLocation) {
        //直接通过类路径找到框架主配置文件的路径
        //并将配置文件内容读取到properties对象中
        LOGGER.info("Loading configLocation--->path:{} ", contextConfigLocation);
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            contextConfig.load(is);
        } catch (IOException e) {
            LogUtil.getLogger().error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void destroy() {

        LOGGER.info("close all resources...");
//
//        //关闭连接池
//        MyDataSourceImpl.getInstance().close();
//        //注销驱动
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        Driver driver = null;
//        while (drivers.hasMoreElements()) {
//            try {
//                driver = drivers.nextElement();
//                DriverManager.deregisterDriver(driver);
//                LogUtil.getLogger().debug("deregister success : driver {}", driver);
//            } catch (SQLException e) {
//                LogUtil.getLogger().error("deregister failed : driver {}", driver);
//            }
//        }

    }

    /**
     * 将bean对象加载进容器
     * @param packageName 扫描包名
     */
    public void loadBeans(String packageName) {
        // 获得扫描路径下所有类的Class文件存放到HashSet中
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        // 判断Class集合是否非空
        if (ValidationUtil.isEmpty(classSet)) {
            LOGGER.warn("Extract nothing from packageName:" + packageName);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : SpringConstant.BEAN_ANNOTATION) {
                //如果类对象中存在注解则加载进bean容器中
                if (clazz.isAnnotationPresent(annotation)) {
                    LOGGER.debug("load bean: " + clazz.getName());
                    BeanContainer.getInstance().addBean(clazz, ClassUtil.newInstance(clazz, true));
                    //如果注解为Configuration，则需要将该类中被@Bean标记的方法返回的对象也加载进容器中
                    if (Configuration.class == annotation) {
                        loadConfigurationBean(clazz);
                    }
                    if (Controller.class == annotation) {
                        loadControllerBean(clazz);
                    }
                }
            }
        }
    }
    /**
     * 加载配置类中的 Configuration bean对象
     * @param clazz 配置类的class文件
     */
    private void loadConfigurationBean(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 判断遍历到的方法是否有@Bean注解
            if (method.isAnnotationPresent(Bean.class)) {
                Object configuration = BeanContainer.getInstance().getBean(clazz);
                Object bean = null;
                try {
                    // 直接执行方法获得bean
                    bean = method.invoke(configuration);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error("load configuration bean error: {}", e.getMessage());
                    e.printStackTrace();
                }
                // bean不为空加入IOC容器
                if (bean != null) {
                    Class<?> beanClazz = bean.getClass();
                    LOGGER.debug("load bean :{}", beanClazz.getName());
                    BeanContainer.getInstance().addBean(beanClazz, bean);
                }

            }
        }
    }
    /**
     * 将 Controller bean对象加载进容器
     * 针对 Controller 有自己的处理方式
     * @param clazz clazz
     */
    public void loadControllerBean(Class<?> clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        String rootUrl = "";
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
            String[] value = annotation.value();
            rootUrl = value[0];
        }
        RequestURLAdapter urlAdapter =  getRequestUrlAdapter();
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getDeclaredAnnotations();

            if (annotations.length == 0) continue;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == RequestMapping.class) {
                    RequestMapping annotation1 = (RequestMapping) annotation;
                    String[] value = annotation1.value();
                    String url = rootUrl + value[0];

                    urlAdapter.putUrl(url, method);
                    urlAdapter.putClass(url,clazz);
                }
            }
        }
        BeanContainer.getInstance().addBean(clazz, ClassUtil.newInstance(clazz, true));
        setRequestUrlAdapter(urlAdapter);
    }

    /**
     * 获取url处理器
     * @return url处理器
     */
    private RequestURLAdapter getRequestUrlAdapter() {
        Object bean = BeanContainer.getInstance().getBean(RequestURLAdapter.class);
        return bean == null ? new RequestURLAdapter() : (RequestURLAdapter) bean;
    }

    /**
     * set url处理器
     * @param adapter url处理器
     */
    private void setRequestUrlAdapter(RequestURLAdapter adapter) {
       BeanContainer.getInstance().addBean(adapter.getClass(),adapter);
    }
}
