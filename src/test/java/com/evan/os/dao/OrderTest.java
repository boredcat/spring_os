package com.evan.os.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderTest {
    private String dirver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/os?useUnicode=true&characterEncoding=utf8";
    private String userNmae = "root";
    private String password = "root";

    @Test
    public void addOrder(){
        try {
            Class.forName(dirver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,userNmae,password);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("insert into orders values('100002','100001',2,2499,now(),null,null,'刘备','1330000000','成都','代发货')");
            statement.execute("update products set stck=stock-2 where id = '100001'");
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
