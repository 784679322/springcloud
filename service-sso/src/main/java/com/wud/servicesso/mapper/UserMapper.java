package com.wud.servicesso.mapper;

import com.wud.cloud.Dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectByPrimaryKey();

}