package com.zts.service;

import com.zts.entity.Students;
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
public interface StudentsService extends IService<Students> {
    /*
     * 根据姓名模糊查询
     * */
    List<Students> findByName(String name);
    /*
     * 查询所有学生成级
     * */
    List<Students> findScores();

}
