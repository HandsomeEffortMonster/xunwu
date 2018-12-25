package com.lzh.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzh
 * @date 2018/12/23 11:27
 */
@Controller
public class AdminController {
    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/center";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }
}
