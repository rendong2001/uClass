package com.pdsu.servlet;

import com.pdsu.bean.Course;
import com.pdsu.bean.CourseUser;
import com.pdsu.bean.User;
import com.pdsu.dao.CourseDao;
import com.pdsu.dao.CourseUserDao;
import com.pdsu.dao.UserDao;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.PageTools;
import com.pdsu.utils.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cuServlet")
public class CourseUserServlet extends BaseServlet {

    private CourseUserDao courseUserDao = new CourseUserDao();
    private UserDao userDao = new UserDao();
    private CourseDao courseDao = new CourseDao();
    private ResultVo vo;

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void findAllByPage(HttpServletRequest req, HttpServletResponse resp) {
        //接收当前页和每页显示的条数
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        //健壮性判断
        int cp = 1;
        //currentPage 是空  默认给第一页
        if(currentPage != null && !currentPage.equals("")) {
            //parseInt将字符串的整数转成int类型
            cp = Integer.parseInt(currentPage);
        }
        int ps = 3;
        if (pageSize != null && !pageSize.equals("")) {
            ps = Integer.parseInt(pageSize);
        }

        //查询总条数
        int totalSize = courseUserDao.findTotalSize();
        //创建分页工具类对象
        PageTools pt = new PageTools(cp,ps,totalSize);
        //通过pt去查询对应的报名课程信息
        List<CourseUser> lists = courseUserDao.findAllByPage(pt);

        //遍历lists集合
        for (CourseUser cu : lists) {
            //拿到cu的uid和cid
            int cid = cu.getCid();
            int uid = cu.getUid();
            //通过cid去course表查询对应的课程  需要去查询课程表， 就是courseDao去查
            Course course = courseDao.queryCourseByCid(cid);
            //通过uid去user表查询对应的用户   需要去查询用户表， 就是userDao去查
            User user = userDao.queryUserByUid(uid);
            //把查询的user和course在存储到cu中
            cu.setUser(user);
            cu.setCourse(course);
        }
        //把lists集合存储到pt中
        pt.setCourseUserList(lists);
        vo = new ResultVo(200,"查询成功",pt);
        MyUtils.printJson(vo,resp);

    }
}
