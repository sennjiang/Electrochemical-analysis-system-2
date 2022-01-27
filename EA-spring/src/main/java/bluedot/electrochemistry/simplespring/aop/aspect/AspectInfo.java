package bluedot.electrochemistry.simplespring.aop.aspect;

import bluedot.electrochemistry.simplespring.aop.PointcutLocator;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class AspectInfo {

    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;

    public AspectInfo() {
    }

    public AspectInfo(int orderIndex, DefaultAspect aspectObject, PointcutLocator pointcutLocator) {
        this.orderIndex = orderIndex;
        this.aspectObject = aspectObject;
        this.pointcutLocator = pointcutLocator;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public DefaultAspect getAspectObject() {
        return aspectObject;
    }

    public PointcutLocator getPointcutLocator() {
        return pointcutLocator;
    }

    @Override
    public String toString() {
        return "AspectInfo{" +
                "orderIndex=" + orderIndex +
                ", aspectObject=" + aspectObject +
                ", pointcutLocator=" + pointcutLocator +
                '}';
    }
}
