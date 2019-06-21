package com.momo.controller;

import com.momo.dto.AccessTokenDto;
import com.momo.dto.GithubUser;
import com.momo.mapper.UserMapper;
import com.momo.model.User;
import com.momo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setState(state);
        //将code等一系列参数通过post请求github获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        //将获取的accessToken通过get请求对比并调用user API获取用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null && githubUser.getId() != null){
            User user = new User();
            final String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
//            List<User> all = userMapper.findAll();
//            System.out.println(all);
            //登陆成功，存入数据库，相当于存入session，将用户存入cookie中
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            //登陆成功，重新登陆
            return "redirect:/";
        }
    }
}
