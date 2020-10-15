package com.springbook.chapter01.controller;

import com.springbook.chapter01.dao.s1.UserDao1;
import com.springbook.chapter01.dao.s2.UserDao2;
import com.springbook.chapter01.dao.s2.NUserDao2;
import com.springbook.chapter01.dao.s3.ConnectionMaker3;
import com.springbook.chapter01.dao.s3.NConnectionMaker3;
import com.springbook.chapter01.dao.s3.UserDao3;
import com.springbook.chapter01.dao.s4.DaoFactory4;
import com.springbook.chapter01.dao.s4.UserDao4;
import com.springbook.chapter01.dao.s5.DaoFactory5;
import com.springbook.chapter01.dao.s6.UserDao6;
import com.springbook.chapter01.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TestController {
    @GetMapping("/chapter01/s1/userdao")
    public String userdao1() throws SQLException, ClassNotFoundException {
        UserDao1 dao = new UserDao1();

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

    @GetMapping("/chapter01/s2/userdao")
    public String userdao2() throws SQLException, ClassNotFoundException {
        UserDao2 dao = new NUserDao2();

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

    @GetMapping("/chapter01/s3/userdao")
    public String userdao3() throws SQLException, ClassNotFoundException {
        ConnectionMaker3 conn = new NConnectionMaker3();
        UserDao3 dao = new UserDao3(conn);

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

    @GetMapping("/chapter01/s4/userdao")
    public String userdao4() throws SQLException, ClassNotFoundException {
        UserDao4 dao = new DaoFactory4().userDao();

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

    @GetMapping("/chapter01/s5/userdao")
    public String userdao5() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory5.class);
        UserDao4 dao = context.getBean("userDao", UserDao4.class);

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

    @GetMapping("/chapter01/s6/userdao")
    public String userdao6() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory5.class);
        UserDao6 dao = context.getBean("userDao", UserDao6.class);

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
