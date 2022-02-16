package bluedot.electrochemistry.commons.entity;

import java.time.LocalDateTime;

/**
 * 算法库表PO类
 * @Author zero
 * @Create 2022/2/15 19:45
 */
public class AlgorithmsLibrary {
    /**
     * 算法库编号
     */
    private Integer id;

    /**
     * 作者昵称（默认用户昵称 可改）
     */
    private String author;

    /**
     * 算法昵称(默认为算法昵称 可改)
     */
    private String name;

    /**
     * 算法地址 例：/1001/Algo01.java
     */
    private String path;

    /**
     * 算法类型，映射如下：
     * 1->降噪
     * 2->平滑
     * 3->基准线
     * 4->其他
     */
    private String type;

    /**
     * 保存至用户算法的次数
     */
    private Integer saveTimes;

    /**
     * 使用此算法的次数
     */
    private Integer useTimes;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;


    public AlgorithmsLibrary() {
    }

    public AlgorithmsLibrary(Integer id, String author, String name, String path, String type, Integer saveTimes, Integer useTimes, LocalDateTime gmtCreate) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.path = path;
        this.type = type;
        this.saveTimes = saveTimes;
        this.useTimes = useTimes;
        this.gmtCreate = gmtCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSaveTimes() {
        return saveTimes;
    }

    public void setSaveTimes(Integer saveTimes) {
        this.saveTimes = saveTimes;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public LocalDateTime getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "AlgorithmsLibrary{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", saveTimes=" + saveTimes +
                ", useTimes=" + useTimes +
                ", gmtCreate='" + gmtCreate + '\'' +
                '}';
    }

}
