package ru.kirill.cuturl.util;

import java.util.concurrent.TimeUnit;

public enum TimeConverter {

    MILLISECONDS,
    SECONDS,
    MINUTES,
    HOURS,
    DAYS;

    public long convert(long duration) {
        return TimeUnit.valueOf(this.name()).toSeconds(duration);
    }
}
