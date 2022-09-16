package com.pdsu.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pdsu.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

    /**
     * 查询所有用户信息
     */
    public List<User> queryAllUser() {
        String sql = "select * from user";
        //执行sql
        try {
            return qr.query(sql, new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 管理员登陆
     * @param username
     * @param password
     * @return
     */

    public User adminLogin(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        List<User> users = new UserDao().queryAllUser();
    }
}
