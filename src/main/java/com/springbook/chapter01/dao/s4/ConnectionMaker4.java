package com.springbook.chapter01.dao.s4;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker4 {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
