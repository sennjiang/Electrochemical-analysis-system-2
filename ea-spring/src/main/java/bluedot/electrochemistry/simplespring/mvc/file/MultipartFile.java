package bluedot.electrochemistry.simplespring.mvc.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Senn
 * @create 2022/1/27 19:52
 */
public interface MultipartFile {

    String getName();

    boolean isEmpty();

    long getSize();

    void transferTo(File dest) throws IOException, IllegalStateException;

    InputStream getInputStream() throws IOException;

}
