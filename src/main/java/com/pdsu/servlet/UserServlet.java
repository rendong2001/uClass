package com.pdsu.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsu.bean.User;
import com.pdsu.dao.UserDao;
import com.pdsu.utils.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
    private ResultVo vo;
    private UserDao userDao = new UserDao();
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
}
