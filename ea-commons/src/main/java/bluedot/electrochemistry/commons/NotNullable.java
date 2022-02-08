package bluedot.electrochemistry.commons;

/**
 * @author Senn
 * @create 2022/2/8 19:46
 */
public interface NotNullable {
    /**
     * 全部不为空 则返回 false 有一个为空 则返回true
     * @return true / false
     */
    boolean isNull();
}
