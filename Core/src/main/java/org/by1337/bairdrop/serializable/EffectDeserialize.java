package org.by1337.bairdrop.serializable;

import org.by1337.bairdrop.effect.IEffect;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EffectDeserialize {
    private static final HashMap<String, Class<?>> deserializable = new HashMap<>();
    public static void register(Class<? extends EffectSerializable> clazz){
        deserializable.put(clazz.getName(), clazz);
    }
    @Nullable
    public static IEffect deserialize(Map<String, Object> map){
        try {
            String clazzName = (String) map.get("class");
            Class<?> clazz = deserializable.getOrDefault(clazzName, null);

            if (clazz == null){
                throw new NullPointerException("unknown class: " + clazzName);
            }

            String methodName = "deserialize";
            Class<?>[] parameterTypes = {Map.class};
            Method method = clazz.getMethod(methodName, parameterTypes);

            Object result = method.invoke(null, map);
            return (IEffect) result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
