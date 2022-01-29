package bluedot.electrochemistry.service.query;

/**
 * @author Senn
 * @create 2022/1/28 18:27
 */
public enum Table {
    /**
     * 用户表
     */
    USER("user"),
    /**
     * 用户冻结申请表
     */
    USER_APPLY("user_apply"),
    /**
     * 电化学文件表
     */
    EA_FILE("ea_file"),
    /**
     * 算法申请表
     */
    ALGORITHM_APPLY("algorithm_apply"),
    /**
     * 角色表
     */
    ROLE("role"),
    /**
     * 权限表
     */
    RIGHT("right"),
    /**
     * 用户 角色表
     */
    USER_ROLE("user_role"),
    /**
     * 角色 权限表
     */
    ROLE_RIGHT("role_right"),
    /**
     * 操作记录表
     */
    OPERATION("operation"),
    /**
     * 系统文件表
     */
    SYSTEM_FILE("system_file");
    /**
     * 表名
     */
    String tableName;

    Table(String tableName) {
        this.tableName = tableName;
    }
}
