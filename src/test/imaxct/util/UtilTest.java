package imaxct.util;

import imaxct.model.User;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by imaxct on 17-3-30.
 */
public class UtilTest extends TestCase {

    public void testBeanUtil(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("username", "a");
        map.put("password", "b");
        User user = BeanUtil.toBean(map, User.class);
        if (user != null)
            System.out.println(user.getId() + "\n" + user.getUsername() + "\n" + user.getPassword());
        else System.out.println("null");
    }
}
