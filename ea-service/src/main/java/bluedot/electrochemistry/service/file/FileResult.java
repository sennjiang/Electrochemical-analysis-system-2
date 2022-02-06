package bluedot.electrochemistry.service.file;

/**
 * 文件业务处理结果封装包
 * @Author zero
 * @Create 2022/1/29 17:55
 */
public class FileResult {
    private String str;     //string类型结果保存
    private boolean flag;   //boolean类型结果保存

    @Override
    public String toString() {
        return "FileResult{" +
                "str='" + str + '\'' +
                ", flag=" + flag +
                '}';
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
