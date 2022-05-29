package com.safenar.json.adapter;

public interface JSONAdapter<T> {
    T toObject(String json);
    boolean isJSON(String text);
    String toJSON(T object);
}
