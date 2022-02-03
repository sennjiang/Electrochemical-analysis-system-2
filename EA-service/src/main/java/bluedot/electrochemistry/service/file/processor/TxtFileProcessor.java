package bluedot.electrochemistry.service.file.processor;

import bluedot.electrochemistry.commons.entity.EaFile;

import java.io.File;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public class TxtFileProcessor implements FileProcessor<EaFile> {

    @Override
    public EaFile fileUploads(File file) {
        //TODO 解析Txt 文件
        System.out.println("解析TXT文件...");
        return null;
    }
}
