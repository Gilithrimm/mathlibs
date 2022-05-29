package com.safenar.json.adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrgJSONAdapter<T> implements JSONAdapter<T> {
    private final Class<T> clazz;

    public OrgJSONAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T toObject(String json) {
        JSONObject jsonObject = new JSONObject(json);
        Map<String, Object> map = jsonObject.toMap();
        try {
            Object instance = clazz.getDeclaredConstructors()[0].newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                var field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                if (entry.getValue() instanceof List list) {
                    var array =
                            checkType(field.getType(), list.size());
                    for (int i = 0; i < list.size(); i++) {
                        array[i] = list.get(i);
                    }
                    field.set(instance, array);
                }
                if (isArray(entry.getValue())) {

                    field.set(instance,
                            Arrays.stream(entry.getValue().toString().split(","))
                                    .toArray(Object[]::new));
                }
                field.set(instance, entry.getValue());
            }
            return (T) instance;
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] checkType(Class<?> type,int value) {
        return switch (type.getName()) {
            case "[Ljava.lang.String;" -> new String[value];
            case "[Ljava.lang.Integer;" -> new Integer[value];
            case "[Ljava.lang.Double;" -> new Double[value];
            case "[Ljava.lang.Boolean;" -> new Boolean[value];
            case "[Ljava.lang.Long;" -> new Long[value];
            case "[Ljava.lang.Float;" -> new Float[value];
            case "[Ljava.lang.Short;" -> new Short[value];
            case "[Ljava.lang.Byte;" -> new Byte[value];
            case "[Ljava.lang.Character;" -> new Character[value];
            default -> new Object[value];
        };
    }

    private boolean isArray(Object value) {
        return value.toString().startsWith("[")
                && value.toString().endsWith("]")
                && value.toString().contains(",");
    }

    @Override
    public boolean isJSON(String text) {
        try {
            new JSONObject(text);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toJSON(T object) {
        JSONObject json = new JSONObject(object);
        return json.toString();
    }
}
