package com.springbook.dao;

import com.springbook.domain.User;
import org.h2.command.Prepared;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
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

        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("delete from users");
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }
}

