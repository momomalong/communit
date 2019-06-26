package com.momo.model;

import lombok.Data;

@Data
public class User {
    //用户id
    private Integer id;
    //用户名字
    private String name;
    //用户Github id
    private String accountId;
    //用户是否在线标识
    private String token;
    //用户创建时间
    private Long gmtCreate;
    //用户修改时间
    private Long gmtModified;
    //用户Github 描述
    private String bio;
    //头像
    private String avatarUrl;
}
