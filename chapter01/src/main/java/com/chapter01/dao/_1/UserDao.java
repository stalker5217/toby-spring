package com.chapter01.dao._1;

import com.chapter01.domain.User;

import java.sql.*;

/**
 * 1.1 초난감 DAO
 *
 * JDBC를 이용한 사용자 등록과 조회 기능이 있는 UserDao Class.
 *
 * 분명히 동작하는 코드다.
 * 하지만 이런 식으로 구현하면 형사처벌을 피할 수 없다.
 */

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");

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
        // DB 연결을 위한 Connection을 가져온다.
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");

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
