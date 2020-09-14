package com.springbook.chapter01.dao.s4;

/**
 *  1.4 제어의 역전(IoC)
 *
 *  's3' package에서 Connection UserDao에서 분리하였다.
 *  UserDao에서는 구현부가 분리되었으나 이는 Dao를 테스트해보는 Controller에서 생성되었다.
 *  결국 Connection의 생성 부분은 누군가가 책임져야하는데 이를 위한 클래스가 팩토리 클래스이다.
 *
 *  Factory Class의 역할은 객체의 생성 방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 역할을 한다.
 *  기존의 UserDao 또는 Controller에서 Connection을 생성하는 방식에서, 오브젝트의 생성을 완전히 이관한다.
 *
 *  이로서 이제 UserDao 또는 Controller에서는 그 주도권을 상실한다.
 *  Connection의 생성 등을 주도적으로 결정할 수 없으며 Factory에서 주는대로 써야하는데
 *  이를 제어의 역전(Inverse of Control)이라고 한다.
 *
 *  이 개념은 라이브러리와 프레임워크의 차이점이라고 볼 수도 있다.
 *  라이브러리는 내가 작성한 코드에서 특정 기능만을 끌어다가 사용하여 주도적으로 개발하는 것이고,
 *  프레임워크는 반대로 내가 작성할 코드는 프레임워크의 규격에 맞추어서 작성되어야 하며
 *  내 코드는 프레임워크 위에 올라가서 동작한다.
 */

public class DaoFactory4 {
    public UserDao4 userDao(){
        return new UserDao4(connectionMaker());
    }

    private ConnectionMaker4 connectionMaker(){
        return new NConnectionMaker4();
    }
}
