package com.momo.provider;

import com.alibaba.fastjson.JSON;
import com.momo.dto.AccessTokenDto;
import com.momo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        //将其传入的accessTokenDto对象转化为Json类型数据
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDto));
        //组装请求类型及参数
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        //得到响应的access_token7
        try (Response response = client.newCall(request).execute()) {
            //将响应结果其中的内容转化为字符串
            String string = response.body().string();
            //获取token编码
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
