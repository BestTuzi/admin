package com.zts.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zts.entity.Scores;
import com.zts.entity.Students;
import com.zts.mapper.StudentsMapper;
import com.zts.service.ScoresService;
import com.zts.service.StudentsService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
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
@RequestMapping("/student")
public class StudentsController {
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private StudentsService studentsService;

    /*
    * 查询所有学生
    * */
    @GetMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                          @RequestParam(value = "size",defaultValue = "5",required = true) Integer size){
        PageHelper.startPage(page,size);
        List<Students> list = studentsService.list();
        PageInfo<Students> pageInfo=new PageInfo<Students>(list);
        model.addAttribute("student", pageInfo);
        System.out.println(list);
        System.out.println(pageInfo.getPageSize());
        model.addAttribute("Tag", "findAll");
        return "/student/studentlist";
    }
    /*
    * 跳转到新增学生
    * */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "/student/addstudent";
    }
    /*
    * 新增学生
    * */
    @PostMapping("/add")
    public String addStudent(@RequestParam(value = "name")String name,@RequestParam(value = "sex")String sex,
                             @RequestParam(value = "birthday")String birthday,@RequestParam(value = "address")String address
                             ){
        Students students=new Students();
        students.setStudentName(name);
        students.setStudentSex(sex);
        students.setStudentBirthday(birthday);
        students.setStudentAddress(address);
        studentsService.save(students);
        return "redirect:/student/findAll";
    }
    /*
    * 跳转到修改页面
    * */
    @GetMapping("/toUpdate")
    public String toUpdate(int id, Model model){
        Students byId = studentsService.getById(id);
        model.addAttribute("student", byId);
        return "/student/updatestudent";
    }
    /*
    * 修改学生
    * */
    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam(value = "name")String name,
                                @RequestParam(value = "sex")String sex,
                                @RequestParam(value = "birthday")String birthday,
                                @RequestParam(value = "address")String address,
                                @RequestParam(value = "id") String  id){
        Students students=new Students();
        students.setStudentName(name);
        students.setStudentSex(sex);
        students.setStudentBirthday(birthday);
        students.setStudentAddress(address);
        int i=Integer.parseInt(id);
        students.setStudentId(i);
        studentsService.updateById(students);
        return "redirect:/student/findAll";
    }
    /*
    * 根据姓名模糊查询学生
    * */
    @RequestMapping("/findByName")
    public String findByName(HttpSession session,String name, Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                             @RequestParam(value = "size",defaultValue = "5",required = true) Integer size){
        List<Students> list = studentsService.findByName(name);
        if (list.size()!=0){
            PageInfo<Students> pageInfo=new PageInfo<Students>(list);
            PageHelper.startPage(page,size );
            model.addAttribute("student", pageInfo);
            model.addAttribute("Tag", "findByName");
            return "/student/studentlist";
        }
        else{
            session.setAttribute("erorrMsg", "找不到该学生");
            return "redirect:/student/findAll";
        }
    }

    /*
    * 根据id删除学生
    * */
    @GetMapping("/delStudent")
    public String delStudent(int id){
        studentsService.removeById(id);
        return "redirect:/student/findAll";
    }
    /*
    * 查看学生成绩
    * */
    @GetMapping("/scores")
    public String score(Model model,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                        @RequestParam(value = "size",defaultValue = "3",required = true) Integer size){
        PageHelper.startPage(page, size);
        List<Students> list = studentsService.findScores();
        PageInfo<Students> pageInfo=new PageInfo<Students>(list);
        model.addAttribute("student", pageInfo);
        return "/student/score";
    }


}

