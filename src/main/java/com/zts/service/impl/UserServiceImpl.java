package com.zts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zts.entity.User;
import com.zts.mapper.UserMapper;
import com.zts.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<User>();
        wrapper.eq("user_name", user.getUserName());
        wrapper.eq("user_password",user.getUserPassword());
        return userMapper.selectOne(wrapper);
    }
}
