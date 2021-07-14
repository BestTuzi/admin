package com.zts.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zts.entity.Depart;
import com.zts.entity.Teachers;
import com.zts.service.DepartService;
import com.zts.service.TeachersService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
@Controller
@RequestMapping("/teacher")
public class TeachersController {
    @Autowired
    private TeachersService teachersService;
    @Autowired
    private DepartService departService;

    /*
    * 查询所有教师
    * */
    @GetMapping("/findAll")
    public String  findAll(Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                           @RequestParam(value = "size",defaultValue = "5",required = true) Integer size){
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(page,size );
        List<Teachers> list = teachersService.findAll();
        PageInfo<Teachers> pageInfo=new PageInfo<Teachers>(list);
        model.addAttribute("teacher", pageInfo);
        model.addAttribute("Tag", "findAll");
        System.out.println(pageInfo);
        return "teacher/teacherlist";
    }

    /*
    * 跳转到新增教师页面
    * */
    @GetMapping("/toAdd")
    public ModelAndView toAdd(){
        List<Depart> all = departService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("depart", all);
        System.out.println(all);
        mv.setViewName("/teacher/addteacher");
        return mv;
    }

    /*
    * 添加教师
    * */
    @PostMapping("/add")
    public String addTeacher(@RequestParam(value = "name")String teacherName,@RequestParam(value = "sex")String teacherSex,
                             @RequestParam(value = "birthday")String teacherBirthday,@RequestParam(value = "prof")String teacherProf,
                             @RequestParam(value = "depart") String teacherDepart ){
        Teachers teachers=new Teachers();
        teachers.setTeacherName(teacherName);
        teachers.setTeacherSex(teacherSex);
        teachers.setTeacherBirthday(teacherBirthday);
        teachers.setTeacherProf(teacherProf);
        teachers.setTeacherDepart(teacherDepart);
        System.out.println("表单数据-----"+teachers);
        teachersService.save(teachers);
        return "redirect:/teacher/findAll?page="+2;

    }
    /*
    * 跳转到修改
    * */
    @GetMapping("/toUpdate")
    public String toUpdate(int id,Model model){
        Teachers byId = teachersService.getById(id);
        List<Depart> all = departService.findAll();
        model.addAttribute("depart", all);
        model.addAttribute("teacher",byId);
        return "/teacher/updateteacher";
    }

    /*
    * 修改教师信息
    * */

    @PostMapping("/updateTeacher")
    public String updateTeacher(@RequestParam(value = "name")String teacherName,@RequestParam(value = "sex")String teacherSex,
                                @RequestParam(value = "birthday")String teacherBirthday,@RequestParam(value = "prof")String teacherProf,
                                @RequestParam(value = "depart") String teacherDepart,@RequestParam(value = "teacherId") String id){
        Teachers teachers=new Teachers();
        int i=Integer.parseInt(id);
        teachers.setTeacherName(teacherName);
        teachers.setTeacherSex(teacherSex);
        teachers.setTeacherBirthday(teacherBirthday);
        teachers.setTeacherProf(teacherProf);
        teachers.setTeacherDepart(teacherDepart);
        teachers.setTeacherId(i);
        teachersService.updateById(teachers);
        return "redirect:/teacher/findAll";
    }

    /*
    * 根据姓名查询教师
    * */
    @RequestMapping("/findByName")
    public String findByName(HttpSession session,String name, Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                             @RequestParam(value = "size",defaultValue = "5",required = true) Integer size){
        PageHelper.startPage(page,size);
        List<Teachers> list = teachersService.findByName(name);
        if (list.size()!=0){
            PageInfo<Teachers> pageInfo=new PageInfo<Teachers>(list);
            model.addAttribute("teacher", pageInfo);
            model.addAttribute("Tag", "findByName");
            model.addAttribute("qname", name);
            return "teacher/teacherlist";
        }
        else {
            session.setAttribute("erorrMsg", "找不到该教师");
            return "redirect:/teacher/findAll";
        }

    }
    /*
    * 根据id删除教师
    * */
    @GetMapping("/delTeacher")
    public String delTeacher(int id){
        teachersService.removeById(id);
        return "redirect:/teacher/findAll";
    }
}

