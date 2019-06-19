package com.springboot.demo.controller;

import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制层类
 */

@Controller
@RequestMapping("/hhyUser")
public class HhyUserController {

    @Resource
    private HhyUserService hhyUserService;

    @RequestMapping("/test")
    public String test(Model model){
        //查询数据库中的所有的用户
        List<HhyUser> hhyUser = hhyUserService.findAll();
        model.addAttribute("users",hhyUser);
        return "hhyUser";
    }
}
