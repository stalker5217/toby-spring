package com.chapter01.dao;

import com.chapter01.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // Single instance
    private static UserDao instance;
    private final ConnectionMaker connectionMaker;

    // Constructor을 private로 선언
    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    // ConnectionMaker를 넣어주는 것도 애매하다
    public static synchronized UserDao getInstance(){
        if(instance == null) instance = new UserDao(null);
        return instance;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        // SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = conn.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // SQL Execute
        ps.executeUpdate();

        // Resource Close
        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Connection conn = connectionMaker.makeConnection();

        // SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }
}
