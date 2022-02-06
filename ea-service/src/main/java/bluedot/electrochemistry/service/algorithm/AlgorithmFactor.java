package bluedot.electrochemistry.service.algorithm;

import bluedot.electrochemistry.cache.entity.FileData;
import bluedot.electrochemistry.service.algorithm.en.AlgorithmMethodType;

import java.util.Arrays;


/**
 * 算法业务参数包装类
 * @Author zero
 * @Create 2022/1/29 16:09
 */
public class AlgorithmFactor {
    private AlgorithmMethodType type;    //方法类型
    private String id;             //算法文件id
    private String path;        //算法文件名
    private FileData fileData;

    public AlgorithmMethodType getType() {
        return type;
    }

    public void setType(AlgorithmMethodType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

    public String[] getXs() {
        return fileData.getX();
    }

    public String[] getYs() {
        return fileData.getY();
    }

    @Override
    public String toString() {
        return "AlgorithmFactor{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", fileData=" + fileData +
                '}';
    }
}
