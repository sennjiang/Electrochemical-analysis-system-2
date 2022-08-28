package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.web.controller.base.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

class UserControllerTest extends WebController {

    @Test
    void login() {
        UserController controller = (UserController) BeanContainer.getInstance().getBean(UserController.class);
        Result result = controller.login("123@qq.com", "123456", null);
        Assertions.assertNotNull(result);
    }

    @Test
    void testJedis() {
        Jedis jedis = new Jedis("124.222.216.219", 6379);
        jedis.auth("bluedot");
        System.out.println(jedis.ping());

    }

    @Test
    void test() {
        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });
        queue.add('a');
        queue.add('b');

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.pop();
        stack.peek();

        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("1");
        queue1.poll();
        queue1.peek();

//        Scanner scanner = new Scanner(System.in);
//        int i = scanner.nextInt();
//        String s = scanner.nextLine();
//        String[] s1 = s.split(" ");
//        for (String s2 : s1) {
//
//        }
    }
}