package bluedot.electrochemistry.service.file;

import bluedot.electrochemistry.service.file.processor.FileProcessor;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * @author Senn
 * @create 2022/1/13 22:33
 */
public class FileTask<T> implements Callable<T> {

    FileProcessor processor;

    File file;

    public FileTask(FileProcessor processor) {
        this.processor = processor;
    }

    @Override
    public T call() throws Exception {
        return null;
    }
}
