package bluedot.electrochemistry.service.algorithm.main;

import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.service.algorithm.AlgorithmResult;

import java.lang.reflect.InvocationTargetException;

/**
 * 算法业务
 * @Author zero
 * @Create 2022/1/29 16:13
 */
public interface AlgorithmService {

    void doService(AlgorithmFactor algorithmFactor) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

}
