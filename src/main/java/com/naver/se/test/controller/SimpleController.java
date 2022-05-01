package com.naver.se.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.naver.se.test.model.UserVO;
import com.naver.se.test.service.UserService;
import com.naver.se.test.service.CollectService;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SimpleController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleController.class);
    private final String className = this.getClass().getName();
    private static ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    UserService userService;

    @Autowired
    CollectService collectService;

    @RequestMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("message", "Stranger");
        return "test";
    }

    @GetMapping("/user/{id}")
    public String getUserByID(@PathVariable("id") String userId, Model model) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);

        try {
            logger.info("====Called getUserById===");

            UserVO user = userService.getUserById(userId);
            model.addAttribute("message", user.getUserName());

            Map<String, String> respValues = objectMapper.convertValue(user, Map.class);
            collectService.sendCollection(timestamp, className, methodName, params, respValues);

            logger.info("====return!!===");
            return "test";

        } catch (Exception e) {
            model.addAttribute("error", e.toString());

            Map<String, String> respValues = new HashMap<>();
            respValues.put("Error", e.toString());
            collectService.sendCollection(timestamp, className, methodName, params, respValues);

            logger.error(e.toString());
            return "error";
        }
    }

    @PostMapping("/user/search")
    public String searchUserByParam(@RequestParam Map<String, String> param, Model model) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String methodName = new Throwable().getStackTrace()[0].getMethodName();

        try {
            logger.info("====Called searchUserByParam===");
            List<UserVO> userList = userService.getUserByParams(param);
            model.addAttribute("userList", userList);

            String lists =  userList.toString();
            Map<String, String> respValues = new HashMap<>();
            respValues.put("users", lists);

            collectService.sendCollection(timestamp, className, methodName, param, respValues);
            logger.info("====return!!===");
            return "search";

        } catch (Exception e) {
            model.addAttribute("error", e.toString());

            Map<String, String> respValues = new HashMap<>();
            respValues.put("Error", e.toString());
            collectService.sendCollection(timestamp, className, methodName, param, respValues);

            logger.error(e.toString());
            return "error";
        }
    }


    @PostMapping("/user")
    public String createUser(@RequestParam Map<String, String> param, Model model) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String methodName = new Throwable().getStackTrace()[0].getMethodName();

        try {
            logger.info("====Called Create User===");
            UserVO user = userService.createUser(param);
            model.addAttribute("message", user.getUserName() + " is created!!");

            Map<String, String> respValues = objectMapper.convertValue(user, Map.class);
            collectService.sendCollection(timestamp, className, methodName, param, respValues);

            logger.info("====return!!===");
            return "test";

        } catch (Exception e) {
            model.addAttribute("error", e.toString());

            Map<String, String> respValues = new HashMap<>();
            respValues.put("Error", e.toString());
            collectService.sendCollection(timestamp, className, methodName, param, respValues);
            
            logger.error(e.toString());
            return "error";
        }
    }


}
