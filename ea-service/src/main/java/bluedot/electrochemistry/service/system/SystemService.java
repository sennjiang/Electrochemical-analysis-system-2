package bluedot.electrochemistry.service.system;

/**
 * @author Sens
 * @createDate 2022/1/13 22:57
 */
public interface SystemService {

    /**
     * 系统备份
     */
    void backup();

    /**
     * 系统还原
     */
    void restore();
}
