import bluedot.electrochemistry.simplespring.core.annotation.Param;

import java.util.Arrays;
import java.util.List;

/**
 * @author Senn
 * @createDate 2021/12/16 19:20
 */

public class User {

    private List<String> list = Arrays.asList(new String("1"), new String("1"));

    @Param("name1")
    private String name = null;
    @Param
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "list=" + list +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
