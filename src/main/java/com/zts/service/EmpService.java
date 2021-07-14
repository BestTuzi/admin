package com.zts.service;

import com.zts.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-11
 */
public interface EmpService extends IService<Emp> {

    List<Emp> findByName(String name);
}
