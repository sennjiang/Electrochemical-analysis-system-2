package bluedot.electrochemistry.service.role;


import bluedot.electrochemistry.service.Lifecycle;

/**
 * @author Sens
 * @Create 2021/12/16 18:58
 */
public interface RoleModularity<T> extends Lifecycle {

    /**
     * 创建角色 并为其赋予权限
     */
    void addRole(T t);

    /**
     * 删除角色s
     */
    void deleteRoles(Integer[] ids);
}
