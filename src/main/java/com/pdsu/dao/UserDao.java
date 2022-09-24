package com.pdsu.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pdsu.bean.User;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.PageTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //private QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

    /**
     * 查询所有用户信息
     */
    public List<User> queryAllUser() {
        String sql = "select * from user";
        //执行sql
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(User.class));
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
            return MyUtils.qr.query(sql,new BeanHandler<>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {null,user.getName(),user.getPhone(),
                user.getAge(),user.getSex(),user.getUsername(),
                user.getPassword(),user.getStatus(),user.getCreatetime(),
                user.getRole(),null};
        try {
            return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询总条数
     * @return
     */
    public int queryTotalSize(String search) {
        //查询的结果是函数
        String sql = "select count(*) from user where username like '%" + search + "%'";
        try {
            //ScalarHandler 专门查询聚合函数的  返回结果是Object类型
            //Object----long----int 返回回去
            long l = (long)MyUtils.qr.query(sql,new ScalarHandler<>());
            return (int)l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 分页查询
     * @param pt
     * @return
     */
    public List<User> findByPage(PageTools pt,String search) {
        String sql = "select * from user where username like '%" + search + "%' limit ?,? ";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(User.class),pt.getIndex(),pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除
     * @param uids
     * @return
     */
    public int delAll(String uids) {
        String sql = "delete from user where uid in (" + uids + ")";
        try {
            return MyUtils.qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user) {
        //通过uid进行修改
        String sql = "update user set name=?,phone=?,age=?,sex=?,username=?," +
                "password=?,status=?,role=? where uid=?";
        Object[] params = {user.getName(),user.getPhone(),user.getAge(),
                user.getSex(),user.getUsername(),user.getPassword(),
                user.getStatus(),user.getRole(),user.getUid()};
        try {
            return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过uid查询用户信息
     * @param uid
     * @return
     */
    public User queryUserByUid(int uid) {
        String sql = "select * from user where uid = ?";
        try {
            return MyUtils.qr.query(sql,new BeanHandler<>(User.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
