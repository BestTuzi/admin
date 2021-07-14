package com.zts.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zts.entity.Emp;
import com.zts.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08*/


@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;
    /** 查询所有职工
    * */

    @GetMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                          @RequestParam(value = "size",defaultValue = "3",required = true) Integer size){
        PageHelper.startPage(page,size);
        List<Emp> list = empService.list();
        PageInfo<Emp> pageInfo=new PageInfo<Emp>(list);
        System.out.println(pageInfo.getPageSize());
        model.addAttribute("emp", pageInfo);
        model.addAttribute("Tag", "findAll");
        return "/emp/emplist";
    }
   /* * 跳转到添加页面
    *
*/
    @GetMapping("/toAdd")
    public String toAdd(){
        return "/emp/addemp";
    }
   /* * 添加职工
    * */

    @PostMapping("/add")
    public String add(@RequestParam(value = "name")String name,@RequestParam(value = "sex")String sex,
                      @RequestParam(value = "address")String address,@RequestParam(value = "prof")String prof){

        Emp emp=new Emp();
        emp.setEmpName(name);
        emp.setEmpAddress(address);
        emp.setEmpSex(sex);
        emp.setEmpProf(prof);
        empService.save(emp);
        return "redirect:/emp/findAll";
  }
  /** 跳转到修改
  *
*/
  @GetMapping("/toUpdate")
    public String toUpdate(int id,Model model){
      Emp byId = empService.getById(id);
      model.addAttribute("emp",byId);
      return "emp/upemp";
  }
/*  * 修改职工
  * */

  @PostMapping("/updateEmp")
    public String updateEmp(@RequestParam(value = "name")String name,@RequestParam(value = "sex")String sex,
                            @RequestParam(value = "address")String address,@RequestParam(value = "prof")String prof,
                            @RequestParam(value = "id") String id){
      Emp emp=new Emp();
      emp.setEmpName(name);
      emp.setEmpAddress(address);
      emp.setEmpSex(sex);
      emp.setEmpProf(prof);
      int i=Integer.parseInt(id);
      emp.setEmpId(i);
      empService.updateById(emp);
      return "redirect:/emp/findAll";
  }
  /** 根据姓名模糊查询职工
  * */

  @RequestMapping("/findByName")
  public String findByName(String name,Model model,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                           @RequestParam(value = "size",defaultValue = "3",required = true) Integer size){
      List<Emp> list = empService.findByName(name);
      PageHelper.startPage(page,size );
      PageInfo<Emp> pageInfo=new PageInfo<Emp>(list);
      model.addAttribute("emp",pageInfo);
      model.addAttribute("Tag", "findByName");
      model.addAttribute("qname", name);
      return "/emp/emplist";
  }
  /** 删除职工
  * */

  @GetMapping("/delEmp")
    public String delEmp(int id){
      empService.removeById(id);
      return "redirect:/emp/findAll";
  }

}

