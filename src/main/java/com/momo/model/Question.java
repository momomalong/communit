package com.momo.model;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    //发布标题
    private String title;
    //发布内容
    private String description;
    //发布标签
    private String tag;
    //创建时间
    private Long gmtCreate;
    //修改时间
    private Long gmtModified;
    //发布用户的id
    private Integer creator;
    //评论数
    private Integer commentCount;
    //阅读数
    private Integer viewCount;
    //点赞数
    private Integer likeCount;

}
