package bluedot.electrochemistry.service;


import bluedot.electrochemistry.utils.LogUtil;
import org.slf4j.Logger;

/**
 * @author Senn
 * @create 2022/2/16 19:16
 */
public class ObjectTest {

    private static final Logger LOGGER = LogUtil.getLogger(ObjectTest.class);

    /**
     * 需求
     * 人
     * 可以传递信息,送礼物, 教授知识, 送养宠物。
     * 如何实现此功能?
     * 如果 以后 我还想传递更多东西怎么办 (扩展)??
     */
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        //送礼物
        boolean delivery = p1.delivery(new Gift("巧克力"), p2);
        //传递消息
        boolean flag = p1.delivery(new Message("我知道面向对象了"), p2);
        LOGGER.info("p1: {} delivery: {} flag: {}", p1, delivery, flag);
        LOGGER.info("p2: {}", p2);
    }
}

class Person {

    private static final Logger LOGGER = LogUtil.getLogger(ObjectTest.class);

    /**
     * 所属物
     */
    Transferable belongings = null;

    /**
     * 转交 物品给 其他人
     *
     * @param thing  物品
     * @param person 其他人
     */
    public boolean delivery(Transferable thing, Person person) {
        LOGGER.info("转交 物品给 其他人。物品: {} 人: {}", thing, person);
        return person.receive(thing);
    }

    /**
     * 接收物品 只要是
     * @param thing 物品
     * @return 是否接收成功
     */
    public boolean receive(Transferable thing) {
        LOGGER.info("接收他人转交的物品。物品: {} ", thing);
        return (belongings = thing) != null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "belongings=" + belongings +
                '}';
    }
}

/**
 * 可转交的
 * 任何可以从一个生物转移到另一个生物的 对象
 */
interface Transferable {
}

/**
 * 礼物  可转交的
 */
class Gift implements Transferable {
    String name;

    public Gift(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "name='" + name + '\'' +
                '}';
    }
}
/**
 * 信息  可转交的
 */
class Message implements Transferable {
    String name;

    public Message(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                '}';
    }
}