package imaxct.dao;

import imaxct.model.User;
import imaxct.util.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * Created by imaxct on 17-3-30.
 */
public class UserDao {

    public boolean register(User user){
        return DBUtil.update("insert into user (id, username, password) values (?, ?, ?)",
                user.getId(), user.getUsername(), user.getPassword());
    }

    public User get(String username, String password){
        User user = new User();
        Map<String, Object> map = DBUtil.query("select * from user where username=? and password=?",
                username, password
                );
        return BeanUtil.toBean(map, User.class);
    }

    public List<User> getAll(){
        List<User>users = new ArrayList<User>();
        List<Map<String, Object>>resList = DBUtil.queryList("select * from user");
        for (Map<String, Object>m : resList){
            User user = BeanUtil.toBean(m, User.class);
            users.add(user);
        }
        return users;
    }
}
