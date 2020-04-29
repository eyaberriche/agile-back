package org.isamm.Agile.web;

import org.isamm.Agile.Repository.UserDao;
import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDao userdao;
    @GetMapping("/list/SM")
    public List<User> getUserListByRoleSM() {
        RoleName smrole= RoleName.ROLE_SM;
        return userdao.findByrole(smrole);
    }
    @GetMapping("/list/PO")
    public List<User> getUserListByRolePO() {
        RoleName porole= RoleName.ROLE_PO;
        return userdao.findByrole(porole);
    }
    @GetMapping("/list")
    public List<User> getUserList() {
        RoleName userrole= RoleName.ROLE_USER;
        return userdao.findByrole(userrole);
    }
    @GetMapping("/list/client")
    public List<User> getClientList() {
        RoleName userrole= RoleName.ROLE_CLIENT;
        return userdao.findByrole(userrole);
    }
    @GetMapping("/list/all")
    public List<User> getAllUserList() {
        return userdao.findAll();
    }

}
