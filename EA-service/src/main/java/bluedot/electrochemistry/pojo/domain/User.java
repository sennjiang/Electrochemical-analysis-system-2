package bluedot.electrochemistry.pojo.domain;

/**
 * @author Sens
 * @createDate 2021/12/16 19:20
 */
public class User {
    private String name;
    private Integer age;

    public String getUsername() {
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
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
