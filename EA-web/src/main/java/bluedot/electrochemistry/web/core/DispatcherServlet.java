package bluedot.electrochemistry.web.core;
import bluedot.electrochemistry.commons.factory.MapperFactory;
import bluedot.electrochemistry.utils.ClassUtil;
import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.utils.ValidationUtil;
import bluedot.electrochemistry.simplemybatis.session.defaults.DefaultSqlSessionFactory;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.simplespring.core.RequestURLAdapter;
import bluedot.electrochemistry.simplespring.core.SpringConstant;
import bluedot.electrochemistry.simplespring.core.annotation.*;
import bluedot.electrochemistry.simplespring.filter.FilterAdapter;
import bluedot.electrochemistry.simplespring.inject.DependencyInject;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.DoRequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.DoFileProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.PreRequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.processor.impl.StaticResourceRequestProcessor;
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

    private BeanContainer beanContainer;

    /**
     * 过滤器
     */
    private FilterAdapter filterAdapter;

    @Override
    public void init(ServletConfig servletConfig) {
        LOGGER.info("ready init in dispatcherServlet");



        //读取配置文件，保存属性到contextConfig
        doLoadConfig(servletConfig.getInitParameter("contextConfigLocation"));

        //初始化容器
        beanContainer = BeanContainer.getInstance();

        loadBeans(contextConfig.getProperty("spring.controllerPackage"));

        loadBeans(contextConfig.getProperty("spring.scanPackage"));

        filterAdapter = new FilterAdapter();
        loadBeans(contextConfig.getProperty("spring.filterPackage"));
        //AOP织入
//        new AspectWeaver().doAspectOrientedProgramming();
        //初始化简易mybatis框架，往IoC容器中注入SqlSessionFactory对象
        //TODO 初始化mybatis异常
//        new SqlSessionFactoryBuilder().build(servletConfig.getInitParameter("contextConfigLocation"));

        beanContainer.addBean(DefaultSqlSessionFactory.class,new DefaultSqlSessionFactory());
        beanContainer.addBean(MapperFactory.class,new MapperFactory());

        new DependencyInject().doDependencyInject();

        //初始化请求处理器责任链
        // 预处理的请求处理器
        PROCESSORS.add(new PreRequestProcessor());

        // 静态资源的请求处理器（如果是静态资源让RequestDispatcher自己处理）
        PROCESSORS.add(new StaticResourceRequestProcessor(servletConfig.getServletContext()));

        PROCESSORS.add(new DoFileProcessor());

        DoRequestProcessor doRequestProcessor = new DoRequestProcessor();
        doRequestProcessor.setFilterAdapter(filterAdapter);
        PROCESSORS.add(doRequestProcessor);





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
            LOGGER.error(e.getMessage());
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
                    if (Filter.class == annotation || BeforeFilter.class == annotation || AfterFilter.class == annotation) {
                        loadFilterBean(clazz);
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
        }else {
            return;
        }
        RequestURLAdapter urlAdapter = (RequestURLAdapter) beanContainer.getBeanOrNewInstance(RequestURLAdapter.class);
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                doLoadUrl(urlAdapter, clazz, method, rootUrl);
            } else if (method.isAnnotationPresent(WhiteMapping.class)) {
                doLoadWhiteUrl(urlAdapter, clazz, method, rootUrl);
            }
        }
        beanContainer.addBean(clazz, ClassUtil.newInstance(clazz, true));
        beanContainer.addBean(RequestURLAdapter.class,urlAdapter);
    }

    private void doLoadWhiteUrl(RequestURLAdapter urlAdapter, Class<?> clazz, Method method, String rootUrl) {
        String[] value = method.getAnnotation(WhiteMapping.class).value();
        String url = rootUrl + value[0];
        urlAdapter.putWhiteUrl(url, method);
        urlAdapter.putClass(url,clazz);
    }

    private void doLoadUrl(RequestURLAdapter urlAdapter, Class<?> clazz, Method method, String rootUrl) {
        String[] value = method.getAnnotation(RequestMapping.class).value();
        String url = rootUrl + value[0];
        urlAdapter.putUrl(url, method);
        urlAdapter.putClass(url,clazz);
    }

    /**
     * 将 Filter bean对象加载进容器
     * 针对 Filter 有自己的处理方式
     * @param clazz clazz
     */
    private void loadFilterBean(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Filter.class)) {
            int level = clazz.getAnnotation(Filter.class).value();
            filterAdapter.addBeforeFilter(clazz, level);
            filterAdapter.addAfterFilter(clazz, level);
        }
        if (clazz.isAnnotationPresent(BeforeFilter.class)) {
            int level = clazz.getAnnotation(BeforeFilter.class).value();
            filterAdapter.addBeforeFilter(clazz, level);
        }
        if (clazz.isAnnotationPresent(AfterFilter.class)) {
            int level = clazz.getAnnotation(AfterFilter.class).value();
            filterAdapter.addAfterFilter(clazz, level);
        }
    }
}
