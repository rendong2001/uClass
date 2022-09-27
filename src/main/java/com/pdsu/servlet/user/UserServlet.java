package com.pdsu.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsu.bean.User;
import com.pdsu.dao.UserDao;
import com.pdsu.servlet.base.BaseServlet;
import com.pdsu.utils.MyUtils;
import com.pdsu.utils.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 前台用户的servlet
 */
@WebServlet("/uServlet")
public class UserServlet extends BaseServlet {

    private ResultVo vo;
    private UserDao userDao = new UserDao();
    /**
     * 验证手机号
     * @param req
     * @param resp
     */
    public void validPhone(HttpServletRequest req, HttpServletResponse resp) {
        //1、接收手机号
        String phone = req.getParameter("phone");
        //2、传递给dao层 判断手机号是否存在
        int i = userDao.validPhone(phone);
        //i > 0 说明查询的到 不让注册
        if (i > 0) {
            vo = new ResultVo(500,"手机号已注册");
        }else {
            vo = new ResultVo(200,"手机号可用");

        }
        //3、将结果响应给前台
        MyUtils.printJson(vo,resp);
    }
    /**
     * 用户注册
     * @param req
     * @param resp
     */
    public void userRegist(HttpServletRequest req, HttpServletResponse resp) {
        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //把参数存储到user对象中
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        //添加创建时间
        user.setCreatetime(new Date());
        //添加账号启用状态为1
        user.setStatus(1);
        //添加权限为3
        user.setRole(3);
        //添加到数据库    调用dao的方法
        int i = userDao.userRegist(user);
        if (i > 0) {
            vo = new ResultVo(200,"注册成功");
        }else {
            vo = new ResultVo(500,"注册失败");

        }
        MyUtils.printJson(vo,resp);
    }
    /**
     * 用户登录
     **/
    public void userLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //获取前台传递的username、password、code
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = userDao.userLogin(phone,password);
        if (user == null) {
            //没有查询到数据
            vo = new ResultVo(500,"账号或者密码不正确");
        }else {
            //将用户信息放到session里面
            session.setAttribute("user", user);
            //登陆成功
            vo = new ResultVo(200,"登陆成功");
        }
        //把结果转成json写回去
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(vo);
        resp.getWriter().print(json);
    }
}
