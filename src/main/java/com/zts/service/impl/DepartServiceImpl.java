package com.zts.service.impl;

import com.zts.entity.Depart;
import com.zts.mapper.DepartMapper;
import com.zts.service.DepartService;
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
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {
    @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Depart> findAll() {
        return departMapper.selectList(null);
    }
}
