package com.zts.service;

import com.zts.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-08
 */
public interface UserService extends IService<User> {

    User findByName(User user);

}
