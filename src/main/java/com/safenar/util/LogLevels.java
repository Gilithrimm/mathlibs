package com.safenar.util;

public enum LogLevels {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3);

    private final int level;

    LogLevels(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static LogLevels getLevel(int level) {
        for (LogLevels logLevel : values()) {
            if (logLevel.getLevel() == level) {
                return logLevel;
            }
        }
        throw new IllegalArgumentException("Wrong level id");
    }

    @Override
    public String toString() {
        return "[%s]".formatted(super.toString());
    }
}
