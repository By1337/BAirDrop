package org.by1337.lib;

import java.lang.reflect.Method;

public class AsyncCatcher {
    public static void catchOp(String s){
        try {
            Class<?> clazz = Class.forName("org.spigotmc.AsyncCatcher");
            Method method = clazz.getMethod("catchOp", String.class);
            method.invoke(null, s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
