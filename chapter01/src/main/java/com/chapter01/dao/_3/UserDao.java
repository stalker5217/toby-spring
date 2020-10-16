package com.chapter01.dao._3;

import com.chapter01.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 1.3 DAO의 확장
 *
 * 상속의 단점을 해결하기 위해,
 * Connection 연결 부분을을 완전히 별도의 독립 클래스로 분리한다. (ConnectionMaker3)
 *
 * 객체 지향 설계 중 'Open-Closed Principle' 개방 폐쇄 원칙은,
 * '클래스나 모듈은 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.' 라는 것이다.
 *
 * Connection 부분을 완전히 분리하면서 DB 연결에 대한 부분은 확장에 열려있다고 보며,
 * Dao의 내부는 그런 부분에 영향을 받지 않고 유지할 수 있으므로 변경에는 닫혀있다고 본다.
 *
 * 또, 객체지향 프로그래밍에 있어 지켜야할 것은 높은 응집도와 낮은 결합도이다.
 *
 * coupling(결합도)
 * 결합도란 서로 다른 모듈들이 얼마나 결합되어 있는가를 나타내며 낮은 결합도를 만드는 것을 지향한다.
 * 결합도가 높다면 특정 모듈을 수정하기 위해서는 그 모듈 외에 결합된 모듈들의 수정도 필연적으로 따라온다.
 *
 * cohesion(응집도)
 * 응집도란 모듈의 내부 기능이 얼마나 밀접하게 연관되어 있는가를 나타내며 높은 응집도를 만드는 것을 지향한다.
 * 하나의 모듈이 이런 기능도하고 저런 기능도하면 이해하고 관리하기 어렵다는 문제점이 발생한다.
 */

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

