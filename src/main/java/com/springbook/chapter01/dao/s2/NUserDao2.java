package com.springbook.chapter01.dao.s2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao2 extends UserDao2 {
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName ("org.h2.Driver");
        return DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");
    }
}
