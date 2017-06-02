package imaxct.servlet;

import imaxct.dao.UserDao;
import imaxct.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/***
 * Created by imaxct on 17-3-30.
 */
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User>users = userDao.getAll();
        StringBuilder res = new StringBuilder();
        for (User user : users){
            res.append(user.getId()).append("\t").append(user.getUsername()).append("\t").append(user.getPassword()).append("\n");
        }
        resp.getWriter().println(res.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
