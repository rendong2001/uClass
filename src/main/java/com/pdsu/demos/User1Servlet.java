package com.pdsu.demos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsu.bean.User;
import com.pdsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class User1Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应的字符集
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        //直接调用dao层，让dao帮我查询user表中所有的数据
        UserDao userDao = new UserDao();
        List<User> userList = userDao.queryAllUser();
        //userList代表表中所有的数据
        System.out.println(userList);
        //需要将userList转成json类型
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userList);
        //将json响应给前台
        resp.getWriter().print(json);
    }
}
