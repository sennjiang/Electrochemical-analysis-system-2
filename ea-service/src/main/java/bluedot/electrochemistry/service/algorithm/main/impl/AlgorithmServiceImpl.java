package bluedot.electrochemistry.service.algorithm.main.impl;

import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.service.algorithm.AlgorithmResult;
import bluedot.electrochemistry.service.algorithm.en.AlgorithmMethodType;
import bluedot.electrochemistry.service.algorithm.main.AlgorithmService;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author zero
 * @Create 2022/1/29 16:25
 */
public class AlgorithmServiceImpl implements AlgorithmService {

    private static final String ALGORITHM_METHOD_NAME = "start";

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

    @Override
    public AlgorithmFactor doService(AlgorithmFactor algorithmFactor) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (algorithmFactor.getId() == null || algorithmFactor.getType() == null || algorithmFactor.getXs() == null || algorithmFactor.getYs() == null) return null;
        if (algorithmFactor.getType() == AlgorithmMethodType.COMPILE) {
            compile(algorithmFactor);
        }else if (algorithmFactor.getType() == AlgorithmMethodType.RUN){
            return run(algorithmFactor);
        }
        return null;
    }

    /**
     * TODO 算法地址 昵称 修改后 可能会出问题
     * @param algorithmFactor algorithmFactor
     * @return boolean
     */
    private boolean compile(AlgorithmFactor algorithmFactor){
        File sonPath = new File(algorithmFactor.getId()+"/"+algorithmFactor.getPath());
        try {
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            File file = new File(getCompilePath(sonPath));
            if (!file.exists()) {
                //todo
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

    //TODO file 是子目录
    private String getCompilePath(File file) {

        return null;
    }
    private AlgorithmFactor run(AlgorithmFactor algorithmFactor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object o = algorithmCacheMap.get(algorithmFactor.getId());
        if (o == null) compile(algorithmFactor);
        o = algorithmCacheMap.get(algorithmFactor.getId());
        if (o == null) return null;
        Method start = o.getClass().getMethod(ALGORITHM_METHOD_NAME, String[].class, String[].class);
        start.invoke(o, algorithmFactor.getXs(), algorithmFactor.getYs());
        return null;
    }
}
