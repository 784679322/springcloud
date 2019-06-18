package com.wud.servicehystrix.service;


import com.wud.cloud.Dto.User;
import com.wud.servicehystrix.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yu on 2019/1/9.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectById() {
        return userMapper.selectByPrimaryKey();
    }
}
