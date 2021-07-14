package com.zts.mapper;

import com.zts.entity.Students;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
public interface StudentsMapper extends BaseMapper<Students> {

    /*
    * 查询所有学生成级
    * */
    List<Students> findScores();

}
