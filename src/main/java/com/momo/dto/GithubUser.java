package com.momo.dto;

import lombok.Data;

@Data
public class GithubUser {
    //Github名字
    private String name;
    //Github描述
    private String bio;
    //Github id
    private Long id;
    //Github头像
    private String avatarUrl;
}
