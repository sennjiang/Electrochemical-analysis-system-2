package bluedot.electrochemistry.service.algorithm.main.impl;

import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.service.algorithm.main.AlgorithmService;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Author zero
 * @Create 2022/1/29 16:25
 */
public class AlgorithmServiceImpl implements AlgorithmService {
    @Override
    public boolean doService(AlgorithmFactor algorithmFactor) {
        if (algorithmFactor.getType().equals("compileAndRun")) return compileAndRun(algorithmFactor);
        return false;
    }

    private boolean compileAndRun(AlgorithmFactor algorithmFactor){
        File sonPath = new File(algorithmFactor.getId()+"/"+algorithmFactor.getName());
        try {

            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            File file = new File(this.getCompilePath(sonPath));
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
                //todo 类名
                Class clz = urlClassLoader.loadClass(algorithmFactor.getName());
                Object obj = clz.newInstance();
                Method method = clz.getDeclaredMethod("start");
                method.invoke(obj);
                System.out.println("算法编译执行成功!");
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
}
