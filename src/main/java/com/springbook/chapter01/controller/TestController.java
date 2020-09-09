package com.springbook.chapter01.controller;

import com.springbook.chapter01.dao.s1.UserDao;
import com.springbook.chapter01.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TestController {
    @GetMapping("/chapter01/userdao1")
    public String userdao1() throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("stalker");
        user.setName("스토커");
        user.setPassword("1234");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공!");

        return user2.getId();
    }
}
