package com.chapter01.dao._2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName ("org.h2.Driver");
        return DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");
    }
}