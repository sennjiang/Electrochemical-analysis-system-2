import bluedot.electrochemistry.simplespring.core.annotation.Param;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;

/**
 * @author Senn
 * @create 2022/1/23 16:56
 */
@Param
public class TestParam {

    public static final String NAME = "Senn";

    public  String str = "str";

    public  int age = 18;


    public String get( @RequestParam("name")String name, String age) {
        return name;
    }

}
