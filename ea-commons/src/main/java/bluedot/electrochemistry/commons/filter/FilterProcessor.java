package bluedot.electrochemistry.commons.filter;

import bluedot.electrochemistry.commons.dao.FilterMapper;
import bluedot.electrochemistry.commons.factory.MapperFactory;
import bluedot.electrochemistry.simplespring.core.BeanContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Senn
 * @create 2022/3/29 21:33
 */
public class FilterProcessor {

    private static Map<String, SetFilter> map = new HashMap<>();

    private static Properties properties;

    private static MapperFactory factory = (MapperFactory) BeanContainer.getInstance().getBean(MapperFactory.class);

    private static ExecutorService threadPool = Executors.newCachedThreadPool(new ThreadFactory() {
        int index;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "filter_thread_" + index++);
        }
    });


    public static void builder(Properties pro) {
        properties = pro;
        init();
    }

    private static void init() {
        FilterMapper filterMapper = factory.createFilterMapper();
        String tables = properties.getProperty("commons.tables");
        String[] ts = tables.split(",");
        for (String t : ts) {
            switch (t) {
                case "user":
                    Integer userMaxId = filterMapper.getUserMaxId();
//                    fitFilter(userMaxId);
                    break;
            }

        }
    }

}
