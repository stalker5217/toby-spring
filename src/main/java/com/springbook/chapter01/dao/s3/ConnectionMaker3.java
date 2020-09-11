package com.springbook.chapter01.dao.s3;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker3 {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
