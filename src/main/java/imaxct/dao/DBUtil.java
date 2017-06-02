package imaxct.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Created by imaxct on 17-3-30.
 */
public class DBUtil {
    private static Context ctx = null;

    private static DataSource ds = null;

    public static boolean update(String sql, Object... objects){
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (objects != null && objects.length>0){
                for (int i=0; i<objects.length; ++i) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            close(connection);
        }
    }

    public static Map<String, Object> query(String sql, Object... objects){
        Connection connection = getConnection();
        Map<String, Object>map = new HashMap<String, Object>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (objects != null && objects.length>0){
                for (int i=0; i<objects.length; ++i) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            int total = rs.getMetaData().getColumnCount();
            if (rs.next()){
                for (int i=1; i<=total; ++i){
                    map.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            return map;
        }finally {
            close(connection);
        }
    }

    public static List<Map<String, Object>> queryList(String sql, Object... objects){
        Connection connection = getConnection();
        List<Map<String, Object>>list = new ArrayList<Map<String, Object>>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            if (objects != null && objects.length>0){
                for (int i=0; i<objects.length; ++i) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            int total = rs.getMetaData().getColumnCount();
            while (rs.next()){
                Map<String, Object>map = new HashMap<String, Object>();
                for (int i=1; i<=total; ++i){
                    map.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }finally {
            close(connection);
        }
    }

    public static Context getInstance(){
        if (ctx == null){
            synchronized (DBUtil.class){
                try {
                    ctx = new InitialContext();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
        return ctx;
    }

    public static DataSource getDataSource(){
        if (ds == null){
            try {
                ds = (DataSource) (getInstance().lookup("java:comp/env/jdbc/DSTest"));
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            connection = getDataSource().getConnection();
            if (connection == null){
                throw new SQLException("fail to get connection.");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement ps){
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
