package bluedot.electrochemistry.service.query;

import java.util.List;

/**
 * @author Senn
 * @create 2022/1/13 12:16
 */
public class SearchResult<E> {
    private int count;
    private List<E> list;
    private SelectType type;

    public SearchResult(int count, List<E> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<E> getList() {
        return list;
    }
}
