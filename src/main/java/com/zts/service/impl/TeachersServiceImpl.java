package com.zts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zts.entity.Teachers;
import com.zts.mapper.TeachersMapper;
import com.zts.service.TeachersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
@Service
public class TeachersServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements TeachersService {

    @Autowired
    private TeachersMapper teachersMapper;
    @Override
    public List<Teachers> findAll()
    {
        return teachersMapper.selectList(null);
    }

    @Override
    public List<Teachers> findByName(String name) {
        QueryWrapper<Teachers> wrapper=new QueryWrapper<>();
        List<Teachers> list = teachersMapper.selectList(wrapper.like("teacher_name", name));
        return list;
    }

    @Override
    public void updateDepartByDepartId(int id, String name) {
        teachersMapper.updateDepartByDepartId(id,name );
    }
}
