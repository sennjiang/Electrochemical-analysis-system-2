package bluedot.electrochemistry.service.base;

import bluedot.electrochemistry.service.dao.base.BaseDao;
import bluedot.electrochemistry.simplespring.core.annotation.Service;

/**
 * @author Senn
 * @create 2022/1/13 13:52
 */
@Service
public class EditServiceImpl implements EditService{

    /**
     * 增删改
     */
    BaseDao baseDao;

    /**
     * param
     * @param param 参数
     */
    private boolean add(EditParam param) {
        return false;
    }

    /**
     * param
     * @param param 参数
     */
    private boolean update(EditParam param) {
        return false;
    }

    /**
     * param
     * @param param 参数
     */
    private boolean delete(EditParam param) {
        return false;
    }


    @Override
    public boolean doEdit(EditParam param) {
        if (EditParam.ADD.equals(param.getType())){
            add(param);
        }
        if (EditParam.DELETE.equals(param.getType())){
            delete(param);
        }
        if (EditParam.UPDATE.equals(param.getType())){
            update(param);
        }

        return false;
    }
}
