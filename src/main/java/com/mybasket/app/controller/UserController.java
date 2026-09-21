package com.mybasket.app.controller;


import com.mybasket.app.entity.User;
import com.mybasket.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

@Autowired
    private UserRepository userRepository;

    @RequestMapping()
    @ResponseBody
public List<User> getUser(){
        System.out.println("getting user");

//        List<String> users= List.of("ankit","saurabh");

//        User user = new User();
//        user.setEmail("test@gmail.com");
//        user.setName("testName");
//        user.setUserId(231232);
//        user.setUserImageUrl("abc.png");


        return userRepository.findAll();
}


}
