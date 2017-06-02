package imaxct.servlet;

import imaxct.dao.UserDao;
import imaxct.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Created by imaxct on 17-3-30.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.get(username, password);
        if (user != null){
            req.getSession().setAttribute("user", user);
            resp.getWriter().println("登录成功");
        }else {
            resp.getWriter().println("登录失败");
        }
    }
}
