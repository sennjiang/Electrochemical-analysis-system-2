package bluedot.electrochemistry.simplespring.mvc.file;

import bluedot.electrochemistry.utils.LogUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Senn
 * @create 2022/1/27 19:57
 */
public class CommonsMultipartFile implements MultipartFile {

    private static final Logger LOGGER = LogUtil.getLogger("spring.mvc");

    FileItem fileItem;

    private final long size;

    public CommonsMultipartFile(FileItem fileItem, long size) {
        this.fileItem = fileItem;
        this.size = size;
    }

    @Override
    public String getName() {
        return fileItem.getName();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        if (!isAvailable()) {
            throw new IllegalStateException("File has already been moved - cannot be transferred again");
        }

        if (dest.exists() && !dest.delete()) {
            throw new IOException(
                    "Destination file [" + dest.getAbsolutePath() + "] already exists and could not be deleted");
        }

        try {
            this.fileItem.write(dest);
            LogFormatUtils.traceDebug((Log) LOGGER, traceOn -> {
                String action = "transferred";
                if (!this.fileItem.isInMemory()) {
                    action = (isAvailable() ? "copied" : "moved");
                }
                return "Part '" + getName() + "',  filename '" + getName() + "'" +
                        (traceOn ? ", stored " + getStorageDescription() : "") +
                        ": " + action + " to [" + dest.getAbsolutePath() + "]";
            });
        }
        catch (FileUploadException ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
        catch (IllegalStateException | IOException ex) {
            // Pass through IllegalStateException when coming from FileItem directly,
            // or propagate an exception from I/O operations within FileItem.write
            throw ex;
        }
        catch (Exception ex) {
            throw new IOException("File transfer failed", ex);
        }
    }

    private String getStorageDescription() {
        if (this.fileItem.isInMemory()) {
            return "in memory";
        }
        else if (this.fileItem instanceof DiskFileItem) {
            return "at [" + ((DiskFileItem) this.fileItem).getStoreLocation().getAbsolutePath() + "]";
        }
        else {
            return "on disk";
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!isAvailable()) {
            throw new IllegalStateException("File has been moved - cannot be read again");
        }
        InputStream inputStream = this.fileItem.getInputStream();
        return (inputStream != null ? inputStream : StreamUtils.emptyInput());
    }

    private boolean isAvailable() {
        // If in memory, it's available.
        if (this.fileItem.isInMemory()) {
            return true;
        }
        // Check actual existence of temporary file.
        if (this.fileItem instanceof DiskFileItem) {
            return ((DiskFileItem) this.fileItem).getStoreLocation().exists();
        }
        // Check whether current file size is different than original one.
        return (this.fileItem.getSize() == this.size);
    }
}
