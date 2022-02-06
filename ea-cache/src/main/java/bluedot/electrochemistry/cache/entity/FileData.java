package bluedot.electrochemistry.cache.entity;

import java.util.Arrays;

/**
 * @author Senn
 * @create 2022/2/4 20:45
 */
public class FileData {
    private String[] x;
    private String[] y;

    public String[] getX() {
        return x;
    }

    public void setX(String[] x) {
        this.x = x;
    }

    public String[] getY() {
        return y;
    }

    public void setY(String[] y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                '}';
    }
}
