package com.pdsu.servlet;

import com.pdsu.bean.Course;
import com.pdsu.dao.CourseDao;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.PageTools;
import com.pdsu.utils.ResultVo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//因为上传的有文件  所以加上注解才支持文件的上传
@MultipartConfig
@WebServlet("/courseServlet")
public class CourseServlet extends BaseServlet {

    private CourseDao courseDao = new CourseDao();
    private ResultVo vo;
    /**
     * 分页模糊查询
     * @param req
     * @param resp
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) {
        //接收当前页和每页显示的条数
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        //接收模糊搜索的条件
        String search = req.getParameter("search");
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
        //查询总条数  满足search条件
        int totalSize = courseDao.queryTotalSize(search);
        //创建分页工具类对象
        PageTools pt = new PageTools(cp,ps,totalSize);
        //查询课程信息
        List<Course> list = courseDao.findAll(pt,search);
        //把list存储到pt中
        pt.setCourseList(list);
        vo = new ResultVo(200,"查询成功",pt);
        MyUtils.printJson(vo,resp);
    }

    /**
     * 添加课程
     * @param req
     * @param resp
     */
    public void  addCourse(HttpServletRequest req,HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {
        //接收课程5个基础参数到map集合中
        Map<String, String[]> map = req.getParameterMap();
        Course course = new Course();
        //将map中的数据封装到course对象中
        BeanUtils.populate(course,map);

        //获取图片
        Part part = req.getPart("image");
        //获取到图片名称
        String imageName = part.getSubmittedFileName();
        /**
         * 1、张三上传了1.jpg       数据库保存了1.jpg   还将1.jpg保存到本地的磁盘中F://实训//大四上//作业//day14//edupic
         *
         * 2、李四上传了1.jpg  数据库保存了1.jpg   还将1.jpg保存到本地的磁盘中F://实训//大四上//作业//day14//edupic
         * 3、出现的问题:
         *      李四上传的1.jpg在F://实训//大四上//作业//day14//edupic文件夹中已经存在，会覆盖张三上传的1.jpg
         * 4、只需要将上传的图片名称保持不相同即可
         *    通过UUID随机生成36位永远都不会重复的字符串当作图片名称
         *
         *
         */
        //通过uuid拼接图片名称
        imageName = UUID.randomUUID() + imageName;
        //将图片名称保存到course对象里面
        course.setCourseImage(imageName);


        File path = new File("F://实训//大四上//作业//day14//edupic");
        //如果F://实训//大四上//作业//day14//edupic 文件夹不存在  会自动去创建
        if (!path.exists()) {
            path.mkdirs();
        }
        //将图片保存到F://实训//大四上//作业//day14//edupic文件夹
        part.write(path + "/" + imageName);



        //处理视频
        //获取视频
        Part part1 = req.getPart("video");
        //获取到视频名称
        String videoName = part1.getSubmittedFileName();
        //通过uuid拼接视频名称
        videoName = UUID.randomUUID() + videoName;
        //将视频名称保存到course对象里面
        course.setCourseVideo(videoName);
        //将视频保存到F://实训//大四上//作业//day14//edupic文件夹
        part1.write(path + "/" + videoName);

        //将课程信息添加到数据库
        int i = courseDao.addCourse(course);

        if (i > 0) {
            vo = new ResultVo(200,"添加成功");
        }else {
            vo = new ResultVo(500,"添加失败");

        }
        //将结果响应回去
        MyUtils.printJson(vo,resp);
    }
    /**
     * 删除选中
     * @param req
     * @param resp
     */
    public void delAll(HttpServletRequest req,HttpServletResponse resp) {
        //接收cids
        String cids = req.getParameter("cids");
        /**
         * 1、数据库的课程信息删除了  本地文件夹的图片和视频还需要保留吗
         *      也需要删除掉
         * 2、是先去删除本地的图片和视频，还是先删除数据库的数据?
         *      1、先删除数据库， 怎么还知道要删除那个图片和视频呢?
         *      2、先删除本地   先删除本地，万一我删除数据库的时候出错了
         *
         * 3、先去数据库把要删除的数据查询出来，保存到一个变量中
         *      等数据库的数据删除成功之后，在去删除本地
         */

        //1、先把cids的数据查询出来
        List<Course> lists = courseDao.queryCourseByCids(cids);

        //2、去删除数据库的数据
        int i = courseDao.delAll(cids);

        if(i > 0) {
            //批量删除成功
            //再去把本地的图片和视频删掉
            for (Course course : lists) {
                //拿到课程的图片名称和视频名称
                String courseImage = course.getCourseImage();
                String courseVideo = course.getCourseVideo();
                //删除
                new File("D://eduPic/" + courseImage).delete();
                new File("D://eduPic/" + courseVideo).delete();
            }

            vo = new ResultVo(200,"批量删除成功");
        }else {
            //批量删除失败
            vo = new ResultVo(500,"批量删除失败");
        }
        MyUtils.printJson(vo,resp);
    }
}
