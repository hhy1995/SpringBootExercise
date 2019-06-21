package com.springboot.demo.controller;

import com.springboot.demo.error.BusinessException;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyMoodService;
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
    @Resource
    private HhyMoodService hhyMoodService;

    @RequestMapping("/test")
    public String test(Model model){
        //查询数据库中的所有的用户
        List<HhyUser> hhyUser = hhyUserService.findAll();
        model.addAttribute("users",hhyUser);
        return "hhyUser";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        //查询数据库中的所有的用户
        List<HhyUser> hhyUser = hhyUserService.findAll();
        model.addAttribute("users",hhyUser);
        throw new BusinessException("业务异常");
    }

    @RequestMapping("/findByNameAndPasswordRetry")
    public String findByNameAndPassword(Model model){
        HhyUser hhyUser = hhyUserService.findByNameAndPasswordRetry("hhy","hhy");
        model.addAttribute("users",hhyUser);
        return "success";
    }
}
