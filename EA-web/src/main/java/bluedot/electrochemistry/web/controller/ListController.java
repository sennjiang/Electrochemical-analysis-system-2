package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.exception.IllegalIndexException;
import bluedot.electrochemistry.service.query.SearchResult;
import bluedot.electrochemistry.service.query.condition.AccountCondition;
import bluedot.electrochemistry.service.query.main.QueryService;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.util.JsonUtil;

/**
 * @author Senn
 * @create 2021/12/26 17:10
 */
@Controller
@WhiteMapping("/list")
public class ListController {

    @Autowired
    QueryService searchService;

    @WhiteMapping("/users")
    String getAccountList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        //     /list/users
//        condition.setPage(SearchPage.ACCOUNT_PAGE);
        SearchResult<?> searchResult = searchService.doService(condition);
        return JsonUtil.toJson(searchResult);
    }

    @WhiteMapping("/algorithms")
    String getAlgorithmList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
       return "";
    }

    @WhiteMapping("/files")
    String getFileList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        return "";
    }

    @WhiteMapping("/roles")
    String getRoleList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        return "";
    }
}
