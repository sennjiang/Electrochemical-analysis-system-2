package bluedot.electrochemistry.web.controller;


import bluedot.electrochemistry.service.exception.IllegalIndexException;
import bluedot.electrochemistry.service.search.SearchPage;
import bluedot.electrochemistry.service.search.condition.AccountCondition;
import bluedot.electrochemistry.service.search.main.SearchService;
import bluedot.electrochemistry.service.search.pages.AccountPage;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;

/**
 * @author Senn
 * @create 2021/12/26 17:10
 */
@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    SearchService searchService;

    @RequestMapping("/users")
    void getAccountList(AccountCondition condition, int pageStart, int pageSize) throws IllegalIndexException {
        //     /list/users
        searchService.doService(condition, SearchPage.ACCOUNT_PAGE);
    }
}
