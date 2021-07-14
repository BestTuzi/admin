package com.zts.service;

import com.zts.entity.Teachers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
public interface TeachersService extends IService<Teachers> {
    /*
     * 查询所有教师
     * */
    List<Teachers> findAll();
    /*
    * 根据姓名模糊查询
    * */

    List<Teachers> findByName(String name);
    /*
    * 根据部门id修改部门名
    * */
    void updateDepartByDepartId(int id,String name);
}
