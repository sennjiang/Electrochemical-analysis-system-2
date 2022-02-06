package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.SelectType;
import bluedot.electrochemistry.service.query.condition.AccountCondition;
import bluedot.electrochemistry.service.query.main.QueryService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.core.annotation.RequestParam;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.util.JsonUtil;
import bluedot.electrochemistry.web.controller.base.BaseController;
import bluedot.electrochemistry.web.controller.base.Result;

/**
 * @author Senn
 * @create 2021/12/26 17:10
 */
@Controller
@RequestMapping("/list")
public class ListController extends BaseController {

    @Autowired
    QueryService searchService;

    @RequestMapping("/users")
    Result getAccountList(@RequestParam("content") String content,
                          @RequestParam("status") String status,
                          @RequestParam("nearly") String nearly,
                          @RequestParam("pageStart") String pageStart,
                          @RequestParam("pageSize") String pageSize) throws IllegalIndexException {
        if (pageStart == null || pageSize == null) return renderBadRequest();
        AccountCondition condition = new AccountCondition();
        condition.setPageStart(Integer.parseInt(pageStart));
        condition.setPageSize(Integer.parseInt(pageSize));
        condition.setType(SelectType.LIST);
        condition.setContent(content);
        condition.setStatus(status);
        condition.setNearly(nearly);
        SearchResult<?> searchResult = searchService.doService(condition);
        return renderSuccess("执行成功",searchResult.getCount(),searchResult.getList());
    }

    @RequestMapping("/algorithms")
    String getAlgorithmList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
       return "";
    }

    @RequestMapping("/files")
    String getFileList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        return "";
    }

    @RequestMapping("/roles")
    String getRoleList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        return "";
    }
}
