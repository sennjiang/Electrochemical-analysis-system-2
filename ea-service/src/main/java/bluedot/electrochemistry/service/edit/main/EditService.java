package bluedot.electrochemistry.service.edit.main;

import bluedot.electrochemistry.service.edit.EditParam;

/**
 * @author Senn
 * @create 2022/1/13 13:48
 */
public interface EditService {

    boolean doEdit(EditParam<?> param);
}
