package com.lzh.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzh
 * @date 2018/12/25 10:13
 */
@Controller
public class UserController {
    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/center")
    public String centerPage(){
        return "user/center";
    }
}
