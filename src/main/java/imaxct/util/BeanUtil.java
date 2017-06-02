package imaxct.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/***
 * 简单bean转换(field 为基础类型)
 * Created by imaxct on 17-3-30.
 */
public class BeanUtil {

    public static  <T> T toBean(Map<String, Object>map, Class<? extends T> clazz){
        if (map == null || map.isEmpty()){
            return null;
        }
        T t;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method setter = clazz.getDeclaredMethod(setterName, field.getType());
                setter.invoke(t, map.get(fieldName));
            }
            return t;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
