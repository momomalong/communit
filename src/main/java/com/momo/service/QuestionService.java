package com.momo.service;

import com.momo.dto.QuestionDto;
import com.momo.mapper.QuestionMapper;
import com.momo.mapper.UserMapper;
import com.momo.model.Question;
import com.momo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> list() {
        List<Question> list = questionMapper.list();
        List<QuestionDto> ls = new ArrayList<>();
        for (Question question : list) {
           User user = userMapper.findById(question.getCreator());
           QuestionDto questionDto = new QuestionDto();
           //拷贝对象属性工具
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            ls.add(questionDto);
        }
        return ls;
    }
}
