package bluedot.electrochemistry.service.algorithm.main.impl;

import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.service.algorithm.en.AlgorithmMethodType;
import bluedot.electrochemistry.service.algorithm.main.AlgorithmService;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import com.sun.xml.internal.bind.v2.TODO;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 算法业务实现类
 * @Author zero
 * @Create 2022/1/29 16:25
 */
@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    //算法文件的默认入口方法名
    private static final String ALGORITHM_METHOD_NAME = "start";

    //线程池
    private static final Executor threadPool = new ThreadPoolExecutor(10,
            100,
            10,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                int index = 0;

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "file_thread_" + index++);
                }
            },
            new ThreadPoolExecutor.AbortPolicy());

    private final Map<String,Object> algorithmCacheMap = new ConcurrentHashMap<>();

    //入口方法
    @Override
    public void doService(AlgorithmFactor algorithmFactor) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (Objects.isNull(algorithmFactor) || algorithmFactor.isNull()) return;
        if (algorithmFactor.getType() == AlgorithmMethodType.COMPILE) {
            compile(algorithmFactor);
        }else if (algorithmFactor.getType() == AlgorithmMethodType.RUN){
            run(algorithmFactor);
        }
    }

    /**
     * 算法文件编译
     * TODO 算法地址 昵称 修改后 可能会出问题
     * @param algorithmFactor 算法业务参数封装类
     * @return boolean  编译是否成功
     */
    private boolean compile(AlgorithmFactor algorithmFactor){
        File sonPath = new File(algorithmFactor.getId()+"/"+algorithmFactor.getPath());
        try {
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            File file = new File(getCompilePath(sonPath));
            if (!file.exists()) {
                //TODO
                System.out.println(file);
                return false;
            }
            //todo 参数列表
            int status = javac.run((InputStream)null, (OutputStream)null, (OutputStream)null, new String[]{"-d", sonPath.toString(), "url"});
            if (status != 0) {
                System.out.println("算法编译失败!");
                return false;
            } else {
                URL url = file.toURI().toURL();
                URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
                //TODO 类名
                Class<?> clz = urlClassLoader.loadClass(algorithmFactor.getPath());
                Object obj = clz.newInstance();
                algorithmCacheMap.put(String.valueOf(algorithmFactor.getId()),obj);
            }
        } catch (Exception var13) {
            System.out.println("算法编译或运行失败:" + var13.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取编译路径
     * @param file 子目录
     * @return 编译路径
     */
    private String getCompilePath(File file) {

        return null;
    }

    /**
     * 算法文件运行
     * @param algorithmFactor 算法业务参数封装类
     * @return boolean 运行是否成功
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException 非法访问异常
     */
    private boolean run(AlgorithmFactor algorithmFactor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = algorithmCacheMap.get(algorithmFactor.getId());
        if (obj == null) compile(algorithmFactor);
        obj = algorithmCacheMap.get(algorithmFactor.getId());
        if (obj == null) return false;
        Method start = obj.getClass().getMethod(ALGORITHM_METHOD_NAME, String[].class, String[].class);
        start.invoke(obj, algorithmFactor.getXs(), algorithmFactor.getYs());
        return false;
    }
}
