package bluedot.electrochemistry.service.algorithm;

import bluedot.electrochemistry.service.algorithm.en.MethodType;

import java.util.Arrays;


/**
 * 算法业务参数包装类
 * @Author zero
 * @Create 2022/1/29 16:09
 */
public class AlgorithmFactor {
    private MethodType type;    //方法类型
    private int id;             //算法文件id
    private String name;        //算法文件名
    private float[] xs;         //横坐标数据
    private float[] ys;         //纵坐标数据

    public AlgorithmFactor(MethodType type, int id, String name, float[] xs, float[] ys) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.xs = xs;
        this.ys = ys;
    }

    public MethodType getType() {
        return type;
    }

    public void setType(MethodType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getXs() {
        return xs;
    }

    public void setXs(float[] xs) {
        this.xs = xs;
    }

    public float[] getYs() {
        return ys;
    }

    public void setYs(float[] ys) {
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
