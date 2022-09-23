package com.safenar.util;

import com.safenar.math.MathException;

@FunctionalInterface
public interface Code {
    void execute() throws MathException;
}
