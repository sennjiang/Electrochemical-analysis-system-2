package bluedot.electrochemistry.service.file.main;

import bluedot.electrochemistry.service.file.FileFactor;
import bluedot.electrochemistry.service.file.FileResult;

/**
 * 文件业务
 * @Author zero
 * @Create 2022/1/29 17:58
 */
public interface FileService {
    public FileResult doService(FileFactor factor);
}
