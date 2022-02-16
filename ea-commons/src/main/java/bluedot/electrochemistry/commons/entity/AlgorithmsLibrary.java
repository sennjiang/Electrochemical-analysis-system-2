package bluedot.electrochemistry.commons.entity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @Author zero
 * @Create 2022/2/15 19:45
 */
public class AlgorithmsLibrary {
    private Integer id;
    private String author;
    private String name;
    private String path;
    private String type;
    private Integer saveTimes;
    private Integer useTimes;
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
