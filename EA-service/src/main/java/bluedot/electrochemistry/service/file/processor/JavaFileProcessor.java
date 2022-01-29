package bluedot.electrochemistry.service.file.processor;

import bluedot.electrochemistry.service.pojo.domain.Algorithm;

import java.io.File;

/**
 * @Author zero
 * @Create 2022/1/29 17:26
 */
public class JavaFileProcessor implements FileProcessor<Algorithm>{

    @Override
    public Algorithm fileUploads(File file) {
        //TODO 解析java 文件
        System.out.println("解析java文件...");
        return null;
    }
}
