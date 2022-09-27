package com.pdsu.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pdsu.bean.Course;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.PageTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CourseDao {

    private QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

    /**
     * 查询总条数
     * @param search
     * @return
     */
    public int queryTotalSize(String search) {
        String sql = "select count(*) from course where courseName like '%" + search + "%'";
        try {
            long l = (long)MyUtils.qr.query(sql,new ScalarHandler<>());
            return (int)l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页模糊查询
     * @param pt
     * @param search
     * @return
     */
    public List<Course> findAll(PageTools pt, String search) {
        String sql = "select * from course where courseName like '%" + search + "%' limit ?,?";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Course.class),
                    pt.getIndex(),pt.getPageSize());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    public int addCourse(Course course) {
        String sql = "insert into course values(?,?,?,?,?,?,?,?,?)";
        Object[] params = {null,course.getCourseName(),course.getDescs(),
                course.getCourseType(),course.getCourseImage(),
                course.getCourseVideo(),course.getCoursePrice(),
                course.getStatus(),new Date()};
        try {
            return MyUtils.qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询数据通过cids
     * @param cids
     * @return
     */
    public List<Course> queryCourseByCids(String cids) {
        String sql = "select * from course where cid in (" + cids + ")";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Course.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除
     * @param cids
     * @return
     */
    public int delAll(String cids) {
        String sql = "delete from course where cid in (" + cids + ")";
        try {
            return MyUtils.qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 查询课程通过cid
     * @param cid
     * @return
     */
    public Course queryCourseByCid(int cid) {
        String sql = "select * from course where cid = ?";
        try {
            Course course = MyUtils.qr.query(sql, new BeanHandler<>(Course.class), cid);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有课程
     * @return
     */
    public List<Course> findAllCourse() {
        String sql = "select * from course";
        try {
            return MyUtils.qr.query(sql,new BeanListHandler<>(Course.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
