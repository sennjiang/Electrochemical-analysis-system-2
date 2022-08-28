import bluedot.electrochemistry.cache.entity.FileData;
import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import bluedot.electrochemistry.web.controller.base.Result;
import bluedot.electrochemistry.web.core.HttpStatus;

import java.util.HashMap;

/**
 * @author Senn
 * @create 2022/2/14 19:30
 */
public class JasonTest {
    public static void main(String[] args) {
        Result result = new Result();
        result.setMessage("1231");
        result.setCode(HttpStatus.OK);
        AlgorithmFactor factor = new AlgorithmFactor();
        FileData fileData = new FileData();
        fileData.setX(new String[] {"1","2"});
        fileData.setY(new String[] {"3","4"});
        factor.setFileData(fileData);
        result.setData(factor);
        System.out.println(JsonUtil.toJson(result));
        String a = new String("abc");
        System.out.println(a.substring(1, 2));

        int i = tableSizeFor(7);
        HashMap<String, String> map = new HashMap<>(7);
        System.out.println(map.size());
        map.put("", "");
        System.out.println(i);
    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}
