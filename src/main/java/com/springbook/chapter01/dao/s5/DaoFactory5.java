package com.springbook.chapter01.dao.s5;

import com.springbook.chapter01.dao.s4.ConnectionMaker4;
import com.springbook.chapter01.dao.s4.NConnectionMaker4;
import com.springbook.chapter01.dao.s4.UserDao4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.5 스프링의 IoC
 *
 * 스프링에서는 스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트를 Bean이라고 한다.
 * 빈의 생성과 관계 설정 같은 제어를 담당하는 IoC 오브젝트를 Bean Factory라고 하며,
 * ApplicationContext를 사용하여 적용 가능하다.
 *
 * 이렇게 Bean으로 구성하였을 때 얻는 장점은 아래와 같다.
 * - 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.
 * - Application Context는 종합 IoC를 제공해준다.
 * - Application Context는 Bean을 검색하는 다양한 방법을 제공한다. (Type 또는 특별한 annotation으로 검색 가능)
 *
 * 용어 정리
 * - Bean : 스프링이 IoC 방식으로 관리하는 오브젝트.
 * - Bean Factory : 스프링의 IoC를 담당하는 핵심 컨테이너.
 *   빈의 등록, 생성, 조회 등을 관리하는 기능을 담당한다. 보통 Application Context를 이용한다.
 * - Application Context : Bean Factory를 확장한 IoC 컨테이너.
 *   이를 Bean Factory라고 부를 때는 주로 빈의 생성과 제어 관점에서 이야기하는 것이고,
 *   Application context라고 부를 때는 스프링이 제공하는 기능을 모두 포함해서 이야기하는 것이라고 보면 된다.
 */

@Configuration
public class DaoFactory5 {
    @Bean
    public UserDao4 userDao(){
            return new UserDao4(connectionMaker());
        }

    @Bean
    public ConnectionMaker4 connectionMaker(){
            return new NConnectionMaker4();
    }
}
