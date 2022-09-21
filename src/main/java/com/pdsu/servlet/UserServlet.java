package com.pdsu.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsu.bean.User;
import com.pdsu.dao.UserDao;
import com.pdsu.utils.PageTools;
import com.pdsu.utils.ResultVo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
    private ResultVo vo;
    private UserDao userDao = new UserDao();

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws IOException
     */
    public void delAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //接收参数
        String uids = req.getParameter("uids");
        //执行sql  userDao
        int i = userDao.delAll(uids);
        if (i > 0) {
            //成功
            vo = new ResultVo(200,"批量删除成功");
        }else {
            //失败
            vo = new ResultVo(500,"批量删除成功");
        }
        //将vo转成json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        //响应给前台
        resp.getWriter().print(json);
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void findByPage(HttpServletRequest req,HttpServletResponse resp) throws IOException {
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
        //查询总条数   满足search条件的总条数 调用dao层的方法
        int totalSize = userDao.queryTotalSize(search);
        //封装分页工具类对象
        PageTools pt = new PageTools(cp, ps, totalSize);
        System.out.println("pt = " + pt);
        //通过pt和模糊搜索的条件去查询前台需要的用户信息
        List<User> lists = userDao.findByPage(pt,search);
        //前台不仅需要lists集合，还需要分页的参数
        //不仅把lists集合给前台，还要把pt给前台
        //可以把lists集合存储到pt中
        pt.setUserList(lists);
        vo = new ResultVo(200,"分页查询成功",pt);
        //将vo转成json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        //响应给前台
        resp.getWriter().print(json);

    }
    /**
     * 添加用户
     * @param req
     * @param resp
     */
    public void addUser(HttpServletRequest req,HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        //接收用户输入的注册信息
        Map<String, String[]> map = req.getParameterMap();//接收所有的参数存储到map集合
        //在把map集合中的数据封装到user对象中
        User user = new User();
        BeanUtils.populate(user,map);
        //向user对象添加当前时间
        user.setCreatetime(new Date());// 如果数据库是8.0版本  这行代码先不写
        //将用户信息保存到数据库中   调用dao层的方法
        int i = userDao.addUser(user);
        if (i > 0) {
            vo = new ResultVo(200,"添加成功");
        }else {
            vo = new ResultVo(500,"添加失败");
        }
        //将vo转成json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);
    }
    /**
     * 查询所有用户信息
     * @param req
     * @param resp
     */
    public void queryAllUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //需要去接收其他参数?  没有参数可以接收
        //调用dao层的方法去查询user表中所有的数据
        List<User> list = userDao.queryAllUser();
        if(list == null) {
            //没有查询到或者报错
            vo = new ResultVo(500,"查询失败");
        }else {
            //查询成功  把list集合放到vo对象中
            vo = new ResultVo(200,"查询成功",list);
        }
        //将vo转成json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);

    }
    /**
     * 管理员登陆
     * @param req
     * @param resp
     */
    public void adminLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //获取前台传递的username、password、code
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        //判断验证码
        //要去获取session中的验证码
        HttpSession session = req.getSession();
        Object verCode = session.getAttribute("verCode");
        //前台传递的code转成小写跟verCode进行比较
        if (!code.toLowerCase().equals(verCode.toString())) {
            //验证码不相等
            vo = new ResultVo(500,"验证码不正确");
        }else {
            //验证码正确
            //通过账号和密码查询数据库  自己不去查询  service去查询
            User user = userDao.adminLogin(username,password);
            if (user == null) {
                //没有查询到数据
                vo = new ResultVo(500,"账号或者密码不正确");
            }else {
                //账号密码正确
                //判断是否封号
                if(user.getStatus() == 2) {
                    //封号了
                    vo = new ResultVo(500,"该账号已封");
                }else {
                    //没有封号
                    if(user.getRole() != 1) {
                        //不是管理员
                        vo = new ResultVo(500,"该账号不是管理员");
                    }else{

                        //将用户信息放到session里面

                        session.setAttribute("user", user);
                        //登陆成功
                        vo = new ResultVo(200,"登陆成功");
                    }
                }
            }
        }

        //把结果转成json写回去
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);
    }

    /**
     * 获取session中用户名
     * @param req
     * @param resp
     */
    public void getSessionUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        //获取session中的user  如果session中有用户信息，直接可以获取到，如果session没有用户信息
        //获取的就是null
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //没有登陆
            vo = new ResultVo(500,"没有账号登陆");
        }else {
            vo = new ResultVo(200,"获取成功",user);
        }

        //把结果转成json写回去
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);
    }

    //管理员退出
    public void removeSessionUser(HttpServletRequest req,HttpServletResponse resp) {
        HttpSession session = req.getSession();
        //将session中的用户信息移除掉
        session.removeAttribute("user");
    }


    /**
     * 更新用户信息
     * @param req
     * @param resp
     */
    public void updateUser(HttpServletRequest req,HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {
        //接收所有参数到map集合中
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        //将map集合的参数封装到user对象中
        BeanUtils.populate(user,map);
        System.out.println(user);

        //修改数据库    userDao去修改
        int i = userDao.updateUser(user);
        if (i > 0) {
            vo = new ResultVo(200,"修改成功");
        }else {
            vo = new ResultVo(500,"修改失败");
        }
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);
    }
}
