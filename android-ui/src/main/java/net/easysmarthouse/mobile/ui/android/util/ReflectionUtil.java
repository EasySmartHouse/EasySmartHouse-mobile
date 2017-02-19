package net.easysmarthouse.mobile.ui.android.util;

import java.lang.reflect.Constructor;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class ReflectionUtil {

    private ReflectionUtil(){
    }

    public static <T> T createInstance(Class<T> cls, Class<?>[] constructorTypes, Object... args) {
        try {

            Constructor<T> cons = null;
            if (args == null || args.length == 0) {
                cons = cls.getConstructor();
            } else {
                cons = cls.getConstructor(constructorTypes);
            }

            cons.setAccessible(true);
            T destination = cons.newInstance(args);
            return destination;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T createInstance(String className, Class<?>[] constructorTypes, Object... args) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            return createInstance(clazz, constructorTypes, args);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
