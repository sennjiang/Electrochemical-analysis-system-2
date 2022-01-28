package bluedot.electrochemistry.service.edit;

import bluedot.electrochemistry.service.dao.base.BaseDao;
import bluedot.electrochemistry.simplespring.core.annotation.Service;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * @author Senn
 * @create 2022/1/13 13:52
 */
@Service
public class EditServiceImpl implements EditService{

    /**
     * 增删改
     */
    @Autowired
    BaseDao baseDao;

    /**
     * param
     * @param param 参数
     */
    private boolean add(EditParam param) {
        return baseDao.insert(param.getT()) == 1;
    }

    /**
     * param
     * @param param 参数
     */
    private boolean update(EditParam param) {
        return baseDao.update(param.getT()) == 1;
    }

    /**
     * param
     * @param param 参数
     */
    private boolean delete(EditParam param) {
        return baseDao.delete(param.getT()) == 1;
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
