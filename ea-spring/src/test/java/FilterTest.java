import bluedot.electrochemistry.simplespring.filter.FilterAdapter;

/**
 * @author Senn
 * @create 2022/1/25 20:32
 */
public class FilterTest {

    public static void main(String[] args) {
        FilterAdapter filterAdapter = new FilterAdapter();
        filterAdapter.addAfterFilter(FilterA.class, 2);
        filterAdapter.addAfterFilter(FilterA.class, 1);
        filterAdapter.addAfterFilter(FilterA.class, 4);
        filterAdapter.addAfterFilter(FilterA.class, 3);

        filterAdapter.addBeforeFilter(FilterB.class, 1);
        filterAdapter.addBeforeFilter(FilterB.class, 4);
        filterAdapter.addBeforeFilter(FilterB.class, 2);
        filterAdapter.addBeforeFilter(FilterB.class, 3);
        filterAdapter.getFilters();
    }

}
