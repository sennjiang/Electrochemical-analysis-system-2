package bluedot.electrochemistry.service.file.main.Impl;

import bluedot.electrochemistry.service.file.FileFactor;
import bluedot.electrochemistry.service.file.FileResult;
import bluedot.electrochemistry.service.file.FileTask;
import bluedot.electrochemistry.service.file.en.FileType;
import bluedot.electrochemistry.service.file.main.FileService;
import bluedot.electrochemistry.service.file.processor.FileProcessor;

import java.io.File;
import java.util.concurrent.*;

/**
 * @Author zero
 * @Create 2022/1/29 18:01
 */
public class FileServiceImpl implements FileService {

    private static final Executor threadPool = new ThreadPoolExecutor(10,
            100,
            10,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                int index = 0;

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "file_thread_" + index++);
                }
            },
            new ThreadPoolExecutor.AbortPolicy());

    @Override
    public FileResult doService(FileFactor factor) {
        FileResult fileResult = new FileResult();
        switch (factor.getmType()) {
            case LOADING :
                fileResult.setStr(loadingData(factor.getId(), factor.getfType()));
                break;
            case UPLOAD :
                fileResult.setFlag(uploadFile(factor.getFiles(),factor.getProcessor()));
                break;
            case EXPORT :
                fileResult.setStr(exportFile(factor.getId(),factor.getfType()));
                break;
            case REMOVE :
                fileResult.setFlag(removeFile(factor.getId(),factor.getfType()));
                break;
            case MODIFY :
                fileResult.setFlag(modifyFile(factor.getId(),factor.getfType(),factor.getFiles()[0]));
                break;
            default :
                fileResult = null;
                System.out.println("方法类型未添加!");
        }
        return fileResult;
    }

    private String loadingData(int id, FileType fType){
        return null;
    }

    private boolean uploadFile(File[] files, FileProcessor<?> processor){

        return false;
    }

    private String exportFile(int id, FileType fType){

        return null;
    }

    private boolean removeFile(int id, FileType fType){
        return false;
    }

    private boolean modifyFile(int id, FileType fType, File file){
        return false;
    }
}
