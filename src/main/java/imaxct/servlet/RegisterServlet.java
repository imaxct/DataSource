package imaxct.servlet;

import imaxct.dao.UserDao;
import imaxct.model.User;
import imaxct.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Created by imaxct on 17-3-30.
 */
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePass = req.getParameter("repass");
        if (Util.isEmpty(username) || Util.isEmpty(password) || Util.isEmpty(rePass)){
            resp.getWriter().println("字段不能为空");
            return;
        }
        if (!password.equals(rePass)){
            resp.getWriter().println("两次密码不同");
            return;
        }

        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        UserDao userDao = new UserDao();
        if (userDao.register(user)){
            resp.getWriter().println("注册成功");
        }else {
            resp.getWriter().println("注册失败");
        }
    }
}
