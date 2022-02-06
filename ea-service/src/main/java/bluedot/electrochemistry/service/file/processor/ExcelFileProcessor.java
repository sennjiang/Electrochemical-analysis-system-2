package bluedot.electrochemistry.service.file.processor;

import bluedot.electrochemistry.commons.entity.EaFile;

import java.io.File;

/**
 * @Author zero
 * @Create 2022/1/29 17:23
 */
public class ExcelFileProcessor implements FileProcessor<EaFile> {

    @Override
    public EaFile fileUploads(File file) {
        //todo 解析Excel文件
        System.out.println("解析excel文件...");
        return null;
    }
}
