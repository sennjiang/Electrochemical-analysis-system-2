package bluedot.electrochemistry.common;

import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */

public class ClassUtil {
    private final static String FILE_PROTOCOL = "file";
    private final static String CLASS_SUFFIX = ".class";
    private final static Logger LOGGER = LogUtil.getLogger("spring.mvc");
    private static final String JAR_PROTOCOL = "jar";

    /**
     * 反射设置bean对象的值
     *
     * @param targetBean bean对象
     * @param field      属性
     * @param fieldValue 值
     * @param accessible 是否允许设置私有属性的值
     */
    public static void setField(Object targetBean, Field field, Object fieldValue, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(targetBean, fieldValue);
        } catch (IllegalAccessException e) {
            LOGGER.error("setField error:" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class对象
     *
     * @param clazz      bean对象的class
     * @param accessible 是否实例化私有构造方法的对象
     * @param <T>        泛型
     * @return bean
     */
    public static <T> T newInstance(Class<T> clazz, Boolean accessible) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(accessible);
            return declaredConstructor.newInstance();
        } catch (Exception e) {
            LOGGER.error("bean newInstance error:" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载类资源
     *
     * @param packageName 需加载类所在的包
     * @return 类资源的set
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        //第一个class类的集合
        Set<Class<?>> classes = new HashSet<>();
        // 获取包的名字 并进行替换
        String pkgDirName = packageName.replace('.', '/');
        try {
            Enumeration<URL> urls = getClassLoader().getResources(pkgDirName);
            if (null == urls) {
                LOGGER.warn("unable to retrieve anything from package: " + packageName);
                return null;
            }
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上（项目中的包）
                if (FILE_PROTOCOL.equals(protocol)) {
                    // 获取
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    File dir = new File(filePath);
                    // 如果不存在或者 也不是目录就直接返回
                    if (!dir.exists() || !dir.isDirectory()) {
                        return null;
                    }
                    extractClassFile(classes, dir, packageName);
                } else if (JAR_PROTOCOL.equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    findClassesByJar(packageName,jar,classes);
                }
            }
        } catch (IOException e) {
           LOGGER.error("get class fail error " + e);
        }
        return classes;
    }

    /**
     * 获取目标package中的所有class文件，包括子package中的文件
     *
     * @param classSet classSet集合
     * @param fileSource 文件源
     * @param filePath 文件地址
     */
    private static void extractClassFile(Set<Class<?>> classSet, File fileSource, String filePath) {
        if (fileSource == null) {
            return;
        }
        for (File file : Objects.requireNonNull(fileSource.listFiles())) {
            if (file.isDirectory()) {
                extractClassFile(classSet, file,filePath + "." + file.getName());
            } else {
                if (file.getName().endsWith(CLASS_SUFFIX)){
                    Class<?> aClass = loadClass(filePath + "." + file.getName().replace(".class", ""));
                    if (aClass != null) {
                        classSet.add(aClass);
                    }
                }
            }
        }
    }

    /**
     * 获取jar包中指定包下的class
     *
     * @param pkgName 包名
     * @param jar     JarFile
     * @param classSet classSet集合
     */
    private static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classSet) {
        String pkgDir = pkgName.replace(".", "/");
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name, className;
        // 同样的进行循环迭代
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }

            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            //如果是一个.class文件 而且不是目录
            // 去掉后面的".class" 获取真正的类名
            className = name.substring(0, name.length() - 6);

            Class<?> aClass = loadClass(className.replace("/", "."));
            // 添加到集合中去
            if (aClass != null) {
                classSet.add(aClass);
            }
        }
    }

    /**
     * 获取当前线程的类加载器
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     * 加载类
     *
     * @param fullClzName 类全名
     * @return Class
     */
    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
