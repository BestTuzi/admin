package com.zts.mapper;

import com.zts.entity.Teachers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
public interface TeachersMapper extends BaseMapper<Teachers> {

    /*
    * 根据部门id修改部门名称
    * */
    void updateDepartByDepartId(@Param("id") int id,@Param("name") String name);


}
