package bluedot.electrochemistry.commons.sender.processor;

import bluedot.electrochemistry.commons.Lifecycle;
import bluedot.electrochemistry.commons.sender.handler.Message;
import bluedot.electrochemistry.commons.sender.handler.SendType;
import bluedot.electrochemistry.commons.sender.handler.SenderHandler;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Senn
 * @create 2022/2/8 19:31
 */
public class SenderProcessor implements Lifecycle {

    private static String VERIFY_CODE_CONTENT;
    private static String USER_FREEZE_INFORM;
    private static String USER_UNFREEZE_INFORM;
    private static String ALGO_APPLY_INFORM;
    private static String ALGO_APPLY_CHECK_INFORM;
    private static String ALGO_LIBRARY_APPLY_INFORM;
    private static String ALGO_LIBRARY_APPLY_CHECK_INFORM;

    private static Properties config;

    private SenderHandler handler;

    public SenderProcessor(Properties properties) {
        config = properties;
    }

    public boolean sender(Message message) {
        if (Objects.isNull(message) || message.isNull()) return false;
        setContent(message);
        return handler.putMessage(message);
    }

    public boolean sender(Message message, long timeout, TimeUnit unit) {
        if (Objects.isNull(message) || message.isNull()) return false;
        setContent(message);
        return handler.putMessage(message, timeout, unit);
    }


    private void setContent(Message message) {
        switch (message.getType()) {
            case VERIFY_CODE:
                message.setContent(VERIFY_CODE_CONTENT + message.getContent());
                break;
            case USER_FREEZE_INFORM:
            case USER_UNFREEZE_INFORM:
            case ALGO_APPLY_INFORM:
            case ALGO_APPLY_CHECK_INFORM:
            case ALGO_LIBRARY_APPLY_INFORM:
            case ALGO_LIBRARY_APPLY_CHECK_INFORM:
            case OTHER:
                break;
        }
    }

    @Override
    public void init() {
        handler = new SenderHandler(config);
        handler.init();
        VERIFY_CODE_CONTENT = config.getProperty("content.verify.code");
        USER_FREEZE_INFORM = config.getProperty("content.freeze");
        USER_UNFREEZE_INFORM = config.getProperty("content.unfreeze");
        ALGO_APPLY_INFORM = config.getProperty("content.algo.apply");
        ALGO_APPLY_CHECK_INFORM = config.getProperty("content.algo.apply.check");
        ALGO_LIBRARY_APPLY_INFORM = config.getProperty("content.algo.library.apply");
        ALGO_LIBRARY_APPLY_CHECK_INFORM = config.getProperty("content.algo.library.apply.check");
    }

    @Override
    public void destroy() {

    }
}
