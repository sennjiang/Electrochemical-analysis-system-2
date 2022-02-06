package bluedot.electrochemistry.service.file;

import bluedot.electrochemistry.service.file.en.FileType;
import bluedot.electrochemistry.service.file.en.MethodType;
import bluedot.electrochemistry.service.file.processor.FileProcessor;

import java.io.File;
import java.util.Arrays;

/**
 * 文件业务参数包装类
 * @Author zero
 * @Create 2022/1/29 17:39
 */
public class FileFactor {
    private MethodType mType;               //方法类型
    private int id;                         //文件id
    private File[] files;                   //文件列表
    private FileProcessor<?> processor;     //文件处理器
    private FileType fType;                 //文件类型

    public FileFactor(MethodType mType, int id, File[] files, FileProcessor<?> processor, FileType fType) {
        this.mType = mType;
        this.id = id;
        this.files = files;
        this.processor = processor;
        this.fType = fType;
    }

    public MethodType getmType() {
        return mType;
    }

    public void setmType(MethodType mType) {
        this.mType = mType;
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

    public FileType getfType() {
        return fType;
    }

    public void setfType(FileType fType) {
        this.fType = fType;
    }

    @Override
    public String toString() {
        return "FileFactor{" +
                "mType=" + mType +
                ", id=" + id +
                ", files=" + Arrays.toString(files) +
                ", processor=" + processor +
                ", fType=" + fType +
                '}';
    }
}
