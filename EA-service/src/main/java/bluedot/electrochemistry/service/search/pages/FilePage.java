package bluedot.electrochemistry.service.search.pages;

import bluedot.electrochemistry.dao.BaseMapper;
import bluedot.electrochemistry.pojo.domain.File;

import java.util.List;

/**
 * @author Senn
 * @create 2022/1/13 12:13
 */
public class FilePage extends AbstractPageSearch<File>{

    @Override
    Integer getCount(BaseMapper mapper, String condition) {
        return null;
    }

    @Override
    List<File> getList(BaseMapper mapper, String condition) {
        return null;
    }
}
