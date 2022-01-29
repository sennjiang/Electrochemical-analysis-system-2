package bluedot.electrochemistry.service.file;

import bluedot.electrochemistry.service.file.processor.FileProcessor;

import java.io.File;
import java.util.Arrays;

/**
 * 文件业务参数包装类
 * @Author zero
 * @Create 2022/1/29 17:39
 */
public class FileFactor {
    private String mType;               //方法类型
    private int id;                     //文件id
    private File[] files;               //文件列表
    private FileProcessor<?> processor;    //文件处理器
    private String fType;               //文件类型

    public FileFactor(String mType, int id, File[] files, FileProcessor<?> processor, String fType) {
        this.mType = mType;
        this.id = id;
        this.files = files;
        this.processor = processor;
        this.fType = fType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public FileProcessor<?> getProcessor() {
        return processor;
    }

    public void setProcessor(FileProcessor<?> processor) {
        this.processor = processor;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    @Override
    public String toString() {
        return "FileFactor{" +
                "mType='" + mType + '\'' +
                ", files=" + Arrays.toString(files) +
                ", processor=" + processor +
                ", fType='" + fType + '\'' +
                '}';
    }
}
