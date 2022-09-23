package com.safenar.values;

import java.lang.reflect.Type;

public interface Value {
    Object getValue();
    Type getType();
}
