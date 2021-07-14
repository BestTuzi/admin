package com.zts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zts.entity.Emp;
import com.zts.mapper.EmpMapper;
import com.zts.service.EmpService;
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
 * @since 2021-07-11
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> findByName(String name) {
        QueryWrapper<Emp> wrapper=new QueryWrapper<Emp>();
        List<Emp> list = empMapper.selectList(wrapper.like("emp_name", name));
        return list;
    }
}
