package imaxct.model;

import java.io.Serializable;

/***
 * Created by imaxct on 17-3-30.
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    public User(){}

    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
