package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.cache.entity.FileData;
import bluedot.electrochemistry.cache.local.FileDataCache;
import bluedot.electrochemistry.commons.entity.AlgorithmLibraryApply;
import bluedot.electrochemistry.commons.factory.CacheExecutorFactory;
import bluedot.electrochemistry.service.algorithm.AlgorithmFactor;
import bluedot.electrochemistry.service.algorithm.en.AlgorithmMethodType;
import bluedot.electrochemistry.service.algorithm.main.AlgorithmService;
import bluedot.electrochemistry.commons.entity.AlgorithmApply;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditType;
import bluedot.electrochemistry.service.edit.main.EditService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.utils.LogUtil;
import bluedot.electrochemistry.web.controller.base.BaseController;
import bluedot.electrochemistry.web.controller.base.Result;
import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

/**
 * 算法-控制器
 * @Author zero
 * @Create 2022/1/29 20:13
 */
@Controller
@RequestMapping("/algorithm")
public class AlgorithmController extends BaseController {

    FileDataCache fileDataCache = CacheExecutorFactory.createFileCache();

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    EditService editService;

    private static final Logger LOGGER = LogUtil.getLogger(AlgorithmController.class);

    @RequestMapping("/use")
    public Result algorithmUse(@RequestParam("algoId") String algoId,
                               @RequestParam("fileId")String fileId) throws ExecutionException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (algoId == null || fileId == null) return renderBadRequest();
        FileData fileData = fileDataCache.get(fileId);
        AlgorithmFactor factor = new AlgorithmFactor();
        factor.setType(AlgorithmMethodType.RUN);
        factor.setId(algoId);
        factor.setFileData(fileData);
        algorithmService.doService(factor);
        return renderSuccess("处理完成",factor.getFileData());
    }

    @RequestMapping("/user/apply")
    public Result addUserApply(@RequestParam("id") String id,
                               @RequestParam("algoName") String algoName,
                               @RequestParam("algoType") String algoType,
                               MultipartFile multipartFile){
        if (id == null || algoName == null || algoType == null || multipartFile.isEmpty()) return renderBadRequest();
        AlgorithmApply algorithmApply = new AlgorithmApply();
        algorithmApply.setUserId(Integer.parseInt(id));
        algorithmApply.setStatus(0);
        algorithmApply.setApplyType(0);
        algorithmApply.setAlgoType(Integer.parseInt(algoType));
        //TODO 写入文件 预处理
        algorithmApply.setPath("TODO");
        boolean b = editService.doEdit(new EditParam<AlgorithmApply>(new AlgorithmApply[]{algorithmApply}, EditType.INSERT));
        return b?renderSuccess("申请成功！") : renderError("申请失败！！");
    }

    @RequestMapping("/user/agree")
    public Result agreeUserApply(@RequestParam("id") String id){
        if (id == null) return renderBadRequest();
        AlgorithmApply algorithmApply = new AlgorithmApply();
        algorithmApply.setId(Integer.parseInt(id));
        algorithmApply.setStatus(1);
        boolean b = editService.doEdit(new EditParam<AlgorithmApply>(new AlgorithmApply[]{algorithmApply}, EditType.INSERT));
        return b ? renderSuccess("申请成功！") : renderError("申请失败！！");
    }

    @RequestMapping("/user/reject")
    public Result rejectUserApply(@RequestParam("id") String id){
        if (id == null) return renderBadRequest();
        AlgorithmApply algorithmApply = new AlgorithmApply();
        algorithmApply.setId(Integer.parseInt(id));
        algorithmApply.setStatus(2);
        boolean b = editService.doEdit(new EditParam<AlgorithmApply>(new AlgorithmApply[]{algorithmApply}, EditType.INSERT));
        return b ? renderSuccess("申请成功！") : renderError("申请失败！！");
    }


    @RequestMapping("/library/apply")
    public Result addLibraryApply(@RequestParam("id") String id,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("algoId") String algoId,
                                  @RequestParam("author")String author,
                                  @RequestParam("algoName")String algoName){
        if (id == null || userId == null || algoId == null || author == null || algoName == null) return renderBadRequest();
        AlgorithmLibraryApply algorithmLibraryApply = new AlgorithmLibraryApply();
        algorithmLibraryApply.setUserId(Integer.parseInt(userId));
        algorithmLibraryApply.setAlgoId(Integer.parseInt(algoId));
        algorithmLibraryApply.setAlgoName(algoName);
        algorithmLibraryApply.setAuthor(author);
        algorithmLibraryApply.setStatus(0);
        boolean b = editService.doEdit(new EditParam<>(new AlgorithmLibraryApply[]{algorithmLibraryApply}, EditType.INSERT));
        return b?renderSuccess("申请成功！!") : renderError("申请失败！！");
    }

    @RequestMapping("/library/reject")
    public Result rejectLibraryApply(@RequestParam("id") String id){
        if (id == null) return renderBadRequest();
        AlgorithmLibraryApply apply = new AlgorithmLibraryApply();
        apply.setId(Integer.parseInt(id));
        apply.setStatus(2);
        boolean b = editService.doEdit(new EditParam<AlgorithmLibraryApply>(new AlgorithmLibraryApply[]{apply}, EditType.INSERT));
        return b ? renderSuccess("申请成功！") : renderError("申请失败！！");
    }
    @RequestMapping("/library/agree")
    public Result agreeLibraryApply(@RequestParam("id") String id){
        if (id == null) return renderBadRequest();
        AlgorithmLibraryApply apply = new AlgorithmLibraryApply();
        apply.setId(Integer.parseInt(id));
        apply.setStatus(1);
        boolean b = editService.doEdit(new EditParam<AlgorithmLibraryApply>(new AlgorithmLibraryApply[]{apply}, EditType.INSERT));
        return b ? renderSuccess("申请成功！") : renderError("申请失败！！");
    }


}
