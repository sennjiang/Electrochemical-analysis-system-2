package bluedot.electrochemistry.service.algorithm;

import java.util.Arrays;


/**
 * 算法业务参数包装类
 * @Author zero
 * @Create 2022/1/29 16:09
 */
public class AlgorithmFactor {
    private String type;    //方法类型
    private int id;         //算法文件id
    private String name;    //算法文件名
    private float[] xs;     //横坐标数据
    private float[] ys;     //纵坐标数据

    @Override
    public String toString() {
        return "AlgorithmFactor{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", xs=" + Arrays.toString(xs) +
                ", ys=" + Arrays.toString(ys) +
                '}';
    }

    public AlgorithmFactor(String type, int id, float[] xs, float[] ys) {
        this.type = type;
        this.id = id;
        this.xs = xs;
        this.ys = ys;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
