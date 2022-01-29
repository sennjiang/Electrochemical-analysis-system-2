package bluedot.electrochemistry.service.file.main.Impl;

import bluedot.electrochemistry.service.factory.MapperFactory;
import bluedot.electrochemistry.service.file.FileFactor;
import bluedot.electrochemistry.service.file.FileResult;
import bluedot.electrochemistry.service.file.main.FileService;
import bluedot.electrochemistry.service.file.processor.FileProcessor;

import java.io.File;

/**
 * @Author zero
 * @Create 2022/1/29 18:01
 */
public class FileServiceImpl implements FileService {

    private MapperFactory mapperFactory;

    @Override
    public FileResult doService(FileFactor factor) {
        FileResult fileResult = new FileResult();
        switch (factor.getmType()) {
            case "loadingData" :
                fileResult.setStr(loadingData(factor.getId(), factor.getfType()));
                break;
            case "uploadFile" :
                fileResult.setFlag(uploadFile(factor.getFiles(),factor.getProcessor()));
                break;
            case "exportFile" :
                fileResult.setStr(exportFile(factor.getId(),factor.getfType()));
                break;
            case "removeFile" :
                fileResult.setFlag(removeFile(factor.getId(),factor.getfType()));
                break;
            case "modifyFile" :
                fileResult.setFlag(modifyFile(factor.getId(),factor.getfType(),factor.getFiles()[0]));
                break;
            default :
                fileResult = null;
                System.out.println("方法类型错误!");
        }
        return fileResult;
    }

    private String loadingData(int id, String fType){
        return null;
    }

    private boolean uploadFile(File[] files, FileProcessor processor){

        return false;
    }

    private String exportFile(int id, String fType){

        return null;
    }

    private boolean removeFile(int id, String fType){
        return false;
    }

    private boolean modifyFile(int id, String fType, File file){
        return false;
    }
}
