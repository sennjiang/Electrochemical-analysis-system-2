package bluedot.electrochemistry.service.pojo.domain;

import bluedot.electrochemistry.simplespring.core.annotation.Param;

/**
 * @author Senn
 * @createDate 2021/12/16 19:20
 */
@Param
public class User {
    private String name;
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
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
