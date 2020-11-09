package com.springbook.dao;

import com.springbook.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    /**
     * Fixture : 테스트를 수행하는 데 필요한 정보나 오브젝트
     * 일반적으로 여러 테스트에서 반복적으로 사용하기 때문에 Before에서 생성해두면 편리하다.
     */
    @Before
    public void setup(){
        user1 = new User("gyumee", "박성철", "spring1");
        user2 = new User("leegw700", "이길원", "spring2");
        user3 = new User("bumjin", "박범진", "spring3");
    }

    /**
     * 저장 결과 Count Test
     */
    @Test
    public void count() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isZero();

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    /**
     * Insert, Read Test
     */
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isZero();

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName()).isEqualTo(user1.getName());
        assertThat(userget1.getPassword()).isEqualTo(user1.getPassword());

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName()).isEqualTo(user2.getName());
        assertThat(userget2.getPassword()).isEqualTo(user2.getPassword());
    }

    /**
     * 예외 상황에 대한 테스트
     * 존재하지 않는 사용자를 읽으려하면 EmptyResultDataAccessException가 발생해야 한다.
     */
    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isZero();

        dao.get("unknown_id");
    }
}
