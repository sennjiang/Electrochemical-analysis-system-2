package bluedot.electrochemistry.service.algorithm;

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
    private String name;        //算法文件名
    private String[] xs;         //横坐标数据
    private String[] ys;         //纵坐标数据

    public AlgorithmFactor(AlgorithmMethodType type, String id, String name, String[] xs, String[] ys) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.xs = xs;
        this.ys = ys;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getXs() {
        return xs;
    }

    public void setXs(String[] xs) {
        this.xs = xs;
    }

    public String[] getYs() {
        return ys;
    }

    public void setYs(String[] ys) {
        this.ys = ys;
    }

    @Override
    public String toString() {
        return "AlgorithmFactor{" +
                "type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", xs=" + Arrays.toString(xs) +
                ", ys=" + Arrays.toString(ys) +
                '}';
    }
}
