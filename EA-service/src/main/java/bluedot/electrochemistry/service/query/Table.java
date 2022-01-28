package bluedot.electrochemistry.service.query;

/**
 * @author Senn
 * @create 2022/1/28 18:27
 */
public enum Table {
    USER("user"),
    USER_APPLY("user_apply"),
    EA_FILE("ea_file"),
    ALGORITHM_APPLY("algorithm_apply"),
    ROLE("role"),
    RIGHT("right"),
    USER_ROLE("user_role"),
    ROLE_RIGHT("role_right"),
    OPERATION("operation"),
    SYSTEM_FILE("system_file");
    String tableName;

    Table(String tableName) {
        this.tableName = tableName;
    }
}
