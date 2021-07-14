package com.zts.service;

import com.zts.entity.Depart;
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
public interface DepartService extends IService<Depart> {

    /*
    * 查询所有部门
    * */

    List<Depart> findAll();

}
