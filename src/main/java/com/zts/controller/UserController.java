package com.zts.controller;


import com.zts.entity.User;
import com.zts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
@Controller

public class UserController {
    @Autowired
    private UserService userService;

    /*
     * 跳转到登录页
     * */
    @RequestMapping({"/","/toLogin"})
    public String toLogin(){
        return "index";
    }

    /*
     * 处理登录请求
     * */
    @RequestMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session, Model model){
        User u=new User();
        u.setUserName(name);
        u.setUserPassword(password);
        User byName = userService.findByName(u);
        System.out.println(byName);
        if (byName!=null){
            session.setAttribute("user",byName);
            session.setAttribute("userStatus", byName.getUserStatus());
            return "redirect:/menu.html";
        }
        else {
            model.addAttribute("erorrMsg","用户名或密码错误");
            return "index";
        }
    }

    /*
     * 防止表单重复提交，定义menu页的刷新
     * */
    @RequestMapping("/menu.html")
    public String menu(){
        return "menu";
    }
    /*
     * 跳转到注册页面
     * */
    @RequestMapping("/toRegist")
    public String toRegist(){
        return "regist";
    }
    /*
     * 用户注册
     * */
    @RequestMapping("/regist")
    public String  regist(@RequestParam("name") String name, @RequestParam("password") String password,Model model){
        User u=new User();
        u.setUserName(name);
        u.setUserPassword(password);
        User byName = userService.findByName(u);
        System.out.println(byName);
        if (byName==null){
            userService.save(u);
            return "index";
        }
        else {
            model.addAttribute("erorrMsg","用户名已存在");
            return "regist";
        }
    }
    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/toLogin";
    }

}

