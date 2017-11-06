package com.gcp.services.task.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
class DAOUtil {
    private Connection conn = null;

    @Value(value = "${database.dbms}")
    private String dbms;
    @Value(value = "${database.driver}")
    private String driver;
    @Value(value = "${database.url}")
    private String url;
    @Value(value = "${database.username}")
    private String username;
    @Value(value = "${database.password}")
    private String password;

    Connection connectToMysql() throws ClassNotFoundException, SQLException, IOException {
        if(conn == null){
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }

        return conn;
    }

    Connection connectToSQLite() throws SQLException, ClassNotFoundException, IOException {
        if(conn == null) {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        }

        return conn;
    }

    void close() throws SQLException {
        conn.close();
        conn = null;
    }

    public void clean() throws SQLException {
        String sqlDelete = "DELETE FROM TASKS";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public String getDbms(){
        return dbms;
    }
}
