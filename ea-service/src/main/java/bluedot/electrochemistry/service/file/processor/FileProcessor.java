package bluedot.electrochemistry.service.file.processor;

import java.io.File;

/**
 * 抽象 文件上传类
 * @author Sens、zero
 * @Create 2021/12/16 18:58
 */
public interface FileProcessor<T> {

    /**
     * 文件上传类
     */
    T fileUploads(File file);

}
