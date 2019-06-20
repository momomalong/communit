package com.momo.mapper;

import com.momo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    //默认修饰符为public 所以可以去掉
    @Insert("insert into user (name,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
