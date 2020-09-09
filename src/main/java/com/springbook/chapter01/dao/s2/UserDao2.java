package com.springbook.chapter01.dao.s2;

import com.springbook.chapter01.domain.User;

import java.sql.*;

/**
 * 1.2 DAO의 분리
 *
 * 관심사의 분리
 * 같은 로직은 하나로 모은다.
 *
 * 1. connection 연결 부분을 하나로 분리해낸다.
 * 2. abstract class로 선언하고,
 *    Connection 구현부를 각 환경에 맞는 DB 연결으로 재정의하게 사용한다.
 *
 * 이렇게 서브 클래스에서 필요에 맞게 구현하도록 하는 방법을
 * 'Template Method Pattern'이라고 한다.
 *
 * 또한, getConnection()은 Connection interface를 반환하는데,
 * 실제 object의 구현은 서브 클래스에서 정의한다.
 * 이렇게 서브 클래스에서 구체적인 오브젝트 생성 방법을 결정하게 넘기는 것을
 * 'Factory Method Pattern'이라고 한다.
 *
 * 하지만, 이 코드도 문제점이 많다.
 * 만약 실제 UserDao 말고 다른 상속이 필요하다면 다중 상속이 안되므로 문제가 된다.
 * 또 다른 Dao가 존재한다면 Connection 부분의 구현이 결국 중복이 발생한다.
 */

public abstract class UserDao2 {
    protected abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

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
        Connection conn = getConnection();

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