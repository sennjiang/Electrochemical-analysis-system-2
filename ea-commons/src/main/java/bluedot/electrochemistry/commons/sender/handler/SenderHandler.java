package bluedot.electrochemistry.commons.sender.handler;

import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.sender.MailSender;
import bluedot.electrochemistry.commons.sender.Sender;
import bluedot.electrochemistry.simplespring.core.BeanContainer;
import bluedot.electrochemistry.utils.LogUtil;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author Senn
 * @create 2022/2/7 20:05
 */
public class SenderHandler implements Lifecycle {

    private static final Logger LOGGER = LogUtil.getLogger(SenderHandler.class);

    private static Properties config;

    ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(100);

    ExecutorService executors =
            new ThreadPoolExecutor(3,
                    3,
                    0,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(1),
                    new ThreadFactory() {
                        int index = 0;

                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "sender-thread-" + index);
                        }
                    },new ThreadPoolExecutor.CallerRunsPolicy());

    public SenderHandler(Properties properties) {
        config = properties;
    }

    public boolean putMessage(Message mail){
        return queue.offer(mail);
    }
    public boolean putMessage(Message mail, long timeout, TimeUnit unit){
        try {
            return queue.offer(mail, timeout, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    private class SenderTask implements Runnable{

        private final Sender sender;

        public SenderTask(Sender sender) {
            this.sender = sender;
        }

        @Override
        public void run() {
            try {
                Message mail;
                while (true) {
                    mail = queue.take();
                    int index = 0;
                    boolean flag = false;
                    while (index++ < 3 && !flag) {
                        Thread.sleep(100);
                        flag = sender.sendMessage(mail.getAccount(), mail.getContent());
                    }
                }
            } catch (InterruptedException | MessagingException e) {
                //do nothing
            }
        }
    }
    @Override
    public void init() {
        //TODO 发送处理器的初始化
//        SenderTask task = new SenderTask(new MailSender("klpjxau@163.com", "NWDATKARUUTXXKJY"));
//        executors.execute(task);
//        BeanContainer.getInstance().addBean(SenderHandler.class, senderHandler);
    }

    @Override
    public void destroy() {

    }
}
