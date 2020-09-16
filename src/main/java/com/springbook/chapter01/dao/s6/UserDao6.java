package com.springbook.chapter01.dao.s6;

import com.springbook.chapter01.dao.s4.ConnectionMaker4;
import com.springbook.chapter01.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 1.6 싱글톤 레지스트리와 오브젝트 스코프
 *
 * ApplicationContext로 UserDao를 가져오는 것은 싱글톤으로 동작한다.
 * UserDao의 역할을 보면 클라이언트마다 개별의 오브젝트를 가질 필요가 없으니,
 * 이러한 구조는 효율적으로 보인다.
 *
 * 그런데, 디자인 패턴에서 흔히 부르는 싱글톤으로 직접 구현한 것에 비해 어떤 장점이 있을까?
 * 1. 생성자가 private이기 때문에 상속이 불가능하다.
 * 2. 테스트하기가 힘들다.
 * 3. 서버 환경에서는 싱글톤이 하나만 만들어지는 것을 보장하지 못한다.
 * 4. 싱글톤의 사용은 전역 상태를 만들 수 있기 때문에 바람직하지 못하다.
 *
 * 스프링은 직접 싱글톤 형태의 오브젝트를 만들고 관리하는 기능을 제공하는데,
 * 이를 Singleton registry라고 부른다.
 */

public class UserDao6 {
    // Single instance
    private static UserDao6 instance;
    private final ConnectionMaker4 connectionMaker;
    
    // Constructor을 private로 선언
    private UserDao6(ConnectionMaker4 connectionMaker){
        this.connectionMaker = connectionMaker;
    }
    // ConnectionMaker를 넣어주는 것도 애매하다
    public static synchronized UserDao6 getInstance(){
        if(instance == null) instance = new UserDao6(null);
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
