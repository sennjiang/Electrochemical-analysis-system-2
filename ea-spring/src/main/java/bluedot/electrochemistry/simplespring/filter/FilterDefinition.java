package bluedot.electrochemistry.simplespring.filter;

/**
 * @author Senn
 * @create 2022/1/25 19:44
 */
public class FilterDefinition implements Comparable<FilterDefinition>{

    private SpringFilter filter;

    private int level = 5;

    public FilterDefinition(SpringFilter filter, int level) {
        this.filter = filter;
        this.level = level;
    }

    public SpringFilter getFilter() {
        return filter;
    }

    public void setFilter(SpringFilter filter) {
        this.filter = filter;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(FilterDefinition o) {
        return this.level - o.getLevel();
    }

    @Override
    public String toString() {
        return "FilterDefinition{" +
                "filter=" + filter +
                ", level=" + level +
                '}';
    }
}
