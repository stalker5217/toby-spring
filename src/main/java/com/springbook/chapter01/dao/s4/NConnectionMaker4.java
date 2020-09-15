package com.springbook.chapter01.dao.s4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker4 implements ConnectionMaker4{
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName ("org.h2.Driver");
        return DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");
    }
}