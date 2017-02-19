package net.easysmarthouse.mobile.ui.android.util;

import java.lang.reflect.Field;

/**
 * Created by rusakovich on 18.02.2017.
 */
public class ResourceHelper {

    private ResourceHelper(){
    }

    public static int getId(String resourceName, Class<?> resourceClass) {
        try {
            Field resField = resourceClass.getDeclaredField(resourceName);
            return resField.getInt(resField);
        } catch (Exception e) {
            throw new RuntimeException("No resource id found for: " + resourceName + " / " +  resourceClass, e);
        }
    }

}
