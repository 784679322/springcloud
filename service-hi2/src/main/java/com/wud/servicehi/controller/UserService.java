package com.wud.servicehi.controller;

import com.wud.cloud.Dto.User;

import java.util.List;

/**
 * Created by yu on 2019/1/9.
 */
public interface UserService {

    public List<User> selectById();

}
