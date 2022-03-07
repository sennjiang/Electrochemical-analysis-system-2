package bluedot.electrochemistry.commons.time.exception;

/**
 * @author Senn
 * @create 2022/3/7 10:57
 */
public class TimeWindowSlidingDataSourceException extends RuntimeException{

    public TimeWindowSlidingDataSourceException() {
    }

    public TimeWindowSlidingDataSourceException(String message) {
        super(message);
    }

    public TimeWindowSlidingDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
