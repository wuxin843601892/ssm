package com.igeek.handler;

import com.igeek.dto.TransferObj;
import com.igeek.pojo.User;
import com.igeek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserHandler {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public TransferObj login(String userName, String password, HttpSession session){
        TransferObj transferObj = new TransferObj();
        System.out.println("login");
        System.out.println(userService);
        User user = userService.findUserByName(userName);
        if(user != null){
            if(user.getPassword().equals(password)){
                session.setAttribute("loginUser", user);
                transferObj.setMessage("success");
            }
            else{
                transferObj.setCode(1);
                transferObj.setMessage("密码错误！");
            }
        }
        else{
            transferObj.setCode(1);
            transferObj.setMessage("用户名不存在！");
        }
        return transferObj;
    }
}
