package com.momo.mapper;

import com.momo.dto.QuestionDto;
import com.momo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //默认修饰符为public 所以可以去掉
    @Insert("insert into user (name,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    //通过cookie判断是否在线
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    //通过id查询用户
    @Select("select * from user where id = #{id}")
//    @Results(id = "userResult", value = {
//            @Result(property = "accountId", column = "account_id", id = true),
//            @Result(property = "gmtCreate", column = "gmt_create"),
//            @Result(property = "gmtModified", column = "gmt_modified"),
//            @Result(property = "avatarUrl", column = "avatar_url")
//    })  也可以通过配置文件配置识别数据库下划线为驼峰
    User findById(@Param("id") Integer id);
}
