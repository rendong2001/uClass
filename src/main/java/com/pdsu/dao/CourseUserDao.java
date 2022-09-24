package com.pdsu.dao;

import com.pdsu.bean.CourseUser;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.PageTools;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseUserDao {
    /**
     * 查询总条数
     * @return
     */
    public int findTotalSize() {
        String sql = "select count(*) from course_user";
        try {
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
    public List<CourseUser> findAllByPage(PageTools pt) {
        String sql = "select * from course_user limit ?,?";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(CourseUser.class),
                    pt.getIndex(),pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
