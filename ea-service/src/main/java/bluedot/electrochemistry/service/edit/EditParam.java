package bluedot.electrochemistry.service.edit;

import bluedot.electrochemistry.commons.NotNullable;

/**
 * @author Senn
 * @create 2022/1/13 13:50
 */
public class EditParam<T> implements NotNullable {

    /**
     * 实体类
     */
    private T[] ts;

    /**
     * 策略
     */
    private EditType type;

    /**
     * 策略
     */
    private EditMultiType multiType;

    public EditParam(T[] ts, EditType type) {
        this.ts = ts;
        this.type = type;
        new EditParam<T>(ts,type, EditMultiType.SINGLE);
    }

    public EditParam(T[] ts, EditType type, EditMultiType multiType) {
        this.ts = ts;
        this.type = type;
        this.multiType = multiType;
    }

    public EditMultiType getMultiType() {
        return multiType;
    }

    public void setMultiType(EditMultiType multiType) {
        this.multiType = multiType;
    }

    public EditType getType() {
        return type;
    }

    public T[] getTs() {
        return this.ts;
    }

    public void setTs(T[] t) {
        this.ts = t;
    }

    public void setType(EditType type) {
        this.type = type;
    }

    @Override
    public boolean isNull() {
        return ts == null || type == null || multiType == null;
    }
}
