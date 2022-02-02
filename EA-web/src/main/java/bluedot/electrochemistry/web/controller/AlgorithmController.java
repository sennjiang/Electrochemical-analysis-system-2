package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.service.edit.EditParam;
import bluedot.electrochemistry.service.edit.EditType;
import bluedot.electrochemistry.service.edit.main.EditService;
import bluedot.electrochemistry.service.pojo.domain.AlgorithmApply;
import bluedot.electrochemistry.service.pojo.domain.AlgorithmLibraryApply;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.web.controller.base.BaseController;
import bluedot.electrochemistry.web.controller.base.Result;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;

/**
 * 算法-控制器
 * @Author zero
 * @Create 2022/1/29 20:13
 */
@Controller
@RequestMapping("/algorithm")
public class AlgorithmController extends BaseController {

    @Autowired
    EditService editService;

    private static final Logger LOGGER = LogUtil.getLogger(AlgorithmController.class);

    @RequestMapping("/use")
    public Result algorithmUse(@RequestParam("algoId") String algoId,
                               @RequestParam("fileId")String fileId){
        if (algoId == null || fileId == null) return renderBadRequest();
        //TODO 数据从缓存中拿取
        return null;
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
        //TODO 写入文件
        algorithmApply.setPath("TODO");
        boolean b = editService.doEdit(new EditParam<AlgorithmApply>(new AlgorithmApply[]{algorithmApply}, EditType.INSERT));
        return b?renderSuccess("申请成功！") : renderError("申请失败！！");
    }

    @RequestMapping("/user/apply")
    public Result deleteUserApply(@RequestParam("id") String id){
        if (id == null) return renderBadRequest();
        AlgorithmApply algorithmApply = new AlgorithmApply();
        algorithmApply.setUserId(Integer.parseInt(id));
        algorithmApply.setStatus(1);
        boolean b = editService.doEdit(new EditParam<AlgorithmApply>(new AlgorithmApply[]{algorithmApply}, EditType.INSERT));
        return b?renderSuccess("申请成功！") : renderError("申请失败！！");
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

}
