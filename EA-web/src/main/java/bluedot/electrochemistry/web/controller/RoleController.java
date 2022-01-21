package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.WhiteMapping;

import javax.management.relation.Role;

/**
 * @author Senn
 * @create 2022/1/13 19:07
 */
@Controller
@WhiteMapping("/role")
public class RoleController {

    @WhiteMapping("/add")
    public String addRole(Role role) {
        return "";
    }

    @WhiteMapping("/delete")
    public String deleteRole(String[] ids) {
        return "";
    }

    /**
     * 分配
     * @param id id
     * @return String
     */
    @WhiteMapping("/distribution")
    public String distributeRole(String id, String roleID) {
        return "";
    }
}
