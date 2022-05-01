package com.naver.se.test.service;

import com.naver.se.test.model.UserVO;
import com.naver.se.test.repository.JpaUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    JpaUserRepository repository;

    public UserVO getUserById(String userId) {
        return repository.findById(userId).orElse(null);
    }

    public List<UserVO> getUserByParams(Map<String, String> param) {
        List<UserVO> userList = repository.findByUserName(param.get("userName"));
        return userList;
    }

    public UserVO createUser(Map<String, String> param) {
        UserVO newUser = new UserVO(param.get("userId"), param.get("userName"), param.get("userEmail"));
        return repository.save(newUser);
    }
}
