package com.zts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zts.entity.Students;
import com.zts.mapper.StudentsMapper;
import com.zts.service.StudentsService;
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
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements StudentsService {

@Autowired
private StudentsMapper studentsMapper;
    @Override
    public List<Students> findByName(String name) {
        QueryWrapper<Students> wrapper=new QueryWrapper<Students>();
        List<Students> list = studentsMapper.selectList(wrapper.like("student_name", name));
        return list;
    }

    @Override
    public List<Students> findScores() {
        return studentsMapper.findScores();
    }
}
