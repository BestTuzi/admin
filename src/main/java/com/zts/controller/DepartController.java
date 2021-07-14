package com.zts.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zts.entity.Depart;
import com.zts.entity.Teachers;
import com.zts.service.DepartService;
import com.zts.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    private DepartService departService;
    @Autowired
    private TeachersService teachersService;


    /*
    * 查询所有部门
    * */
    @GetMapping("/findAll")
    public String findAll(Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                          @RequestParam(value = "size",defaultValue = "3",required = true) Integer size){
        PageHelper.startPage(page, size);
        List<Depart> list = departService.list();
        PageInfo<Depart> pageInfo=new PageInfo<Depart>(list);

        pageInfo.setPageSize(size);
        model.addAttribute("depart",pageInfo);
        return "depart/departlist";
    }
    /*
    * 跳转到添加部门
    * */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "depart/adddepart";
    }
    /*
    * 添加部门
    * */
    @PostMapping("/addDepart")
    public String addDepart(@RequestParam(value = "name") String name,@RequestParam(value = "address") String address){
        Depart depart=new Depart();
        depart.setDepartName(name);
        depart.setDepartAddress(address);
        departService.save(depart);
        return "redirect:/depart/findAll";
    }
    /*
    * 跳转到修改页面
    * */
    @GetMapping("/toUpdate")
    public String toUpdate(Integer id,Model model){
        Depart byId = departService.getById(id);
        model.addAttribute("depart",byId);
        return "depart/updatedepart";
    }
    /*
    * 修改部门信息，教师表中的信息一并修修改
    * */
    @PostMapping("/updateDepart")
    public String updateDepart(@RequestParam(value = "name") String name,@RequestParam(value = "address")String address,
            @RequestParam(value="id") int id){
        Depart depart=new Depart();
        depart.setDepartName(name);
        depart.setDepartAddress(address);
        depart.setDepartId(id);
        departService.updateById(depart);
        teachersService.updateDepartByDepartId(id, name);
        return "redirect:/depart/findAll";
    }
    /*
    * 查看部门教师信息
    * */
    @GetMapping("/getDepartTeachers")
    public String getDepartTeachers(Model model, @RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                    @RequestParam(value = "size",defaultValue = "5",required = true) Integer size,String name){
        PageHelper.startPage(page,size );
        QueryWrapper<Teachers> wrapper=new QueryWrapper<Teachers>();
        wrapper.eq("teacher_depart", name);
        List<Teachers> list = teachersService.list(wrapper);;
        PageInfo<Teachers> pageInfo=new PageInfo<Teachers>(list);
        model.addAttribute("teacher", pageInfo);
        model.addAttribute("qname", name);
        return "/depart/departteachers";
    }

}

