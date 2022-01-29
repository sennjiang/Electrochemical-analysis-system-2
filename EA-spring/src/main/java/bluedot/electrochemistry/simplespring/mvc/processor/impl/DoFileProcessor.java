package bluedot.electrochemistry.simplespring.mvc.processor.impl;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessorChain;
import bluedot.electrochemistry.simplespring.mvc.RequestProcessor;
import bluedot.electrochemistry.simplespring.mvc.file.CommonsMultipartFile;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Senn
 * @create 2022/1/22 17:03
 */
public class DoFileProcessor implements RequestProcessor {

    private static final String REQUEST_FILE_HEAD = "multipart/form-data";

    private static final String REQUEST_FILE_DEFAULT_PATH = "/uploads";

    private static final Logger LOGGER = LogUtil.getLogger("spring.mvc.processor");

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        HttpServletRequest request = requestProcessorChain.getRequest();

        String header = request.getHeader("Content-Type");
        if (header == null || !header.contains(REQUEST_FILE_HEAD)) {
            LOGGER.debug("do fileProcessor there is not a file upload request !");
            return true;
        }
        LOGGER.debug("do fileProcessor to make file...");

        MultipartFile[] files = doFileUpload(request, requestProcessorChain.getRequestParams());

        requestProcessorChain.setRequestParams(requestProcessorChain.getRequest().getParameterMap());
        requestProcessorChain.setRequestFiles(files);
        return true;
    }

    private MultipartFile[] doFileUpload(HttpServletRequest request, Map<String, String[]> requestParams) throws Exception {
        List<MultipartFile> list = new ArrayList<>();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                String value = item.getString();
                String name = item.getFieldName();
                requestParams.put(name,new String[] {value});
            } else {
                MultipartFile multipartFile = new CommonsMultipartFile(item, item.getSize());
                list.add(multipartFile);
            }
        }
        return list.toArray(new MultipartFile[0]);
    }

}
