import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Senn
 * @create 2022/1/24 19:37
 */
public class TestScan {

    public static void main(String[] args) {
        doScanPackage("bluedot.electrochemistry.service");
//        doScanner("E:/Code_Workplace/Electrochemical-analysis-system-2/EA-service/target/classes/bluedot/electrochemistry/service");
    }
    public static Set<Class<?>> doScanPackage(String pkg) {
        //第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();
        // 获取包的名字 并进行替换
        String pkgDirName = pkg.replace('.', '/');
        try {
            Enumeration<URL> urls = TestScan.class.getClassLoader().getResources(pkgDirName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上（项目中的包）
                if ("file".equals(protocol)) {
                    // 获取
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    System.out.println("file filePath:"+filePath);
                    findClassesByFile2(filePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }
    private static void findClassesByFile2(String pkgName) {
        // 获取此包的目录 建立一个File
        File dir = new File(pkgName);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        // 如果存在 就获取包下的所有class
        //FileUtils.listFiles()
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"class"}, true);

        files.forEach(file -> {
            //加载类
            System.out.println("findClassesByFile2 : " + file.getName());
        });
    }
}
