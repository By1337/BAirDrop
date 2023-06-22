package org.by1337.bairdrop.serializable;

import org.by1337.bairdrop.effect.IEffect;

import java.lang.reflect.Method;
import java.util.HashMap;

public class EffectDeserialize {
    private static final HashMap<String, Class<?>> deserializable = new HashMap<>();
    public static void register(Class<?> clazz){
        deserializable.put(clazz.getName(), clazz);
    }
    public static IEffect deserialize(String str){
        try {
            String clazzName = str.split(";")[0];
            Class<?> clazz = deserializable.get(clazzName);

            String methodName = "deserialize";
            Class<?>[] parameterTypes = {String.class};
            Method method = clazz.getMethod(methodName, parameterTypes);

            Object result = method.invoke(null, str);
            return (IEffect) result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
