package ru.kirill.cuturl.util;

import java.util.concurrent.TimeUnit;

public enum TimeConverter {

    MILLISECONDS {
        @Override
        public long convert(long duration) {
            return TimeUnit.MILLISECONDS.toSeconds(duration);
        }
    },
    SECONDS {
        @Override
        public long convert(long duration)  {
            return TimeUnit.SECONDS.toSeconds(duration);
        }
    },
    MINUTES {
        @Override
        public long convert(long duration)  {
            return TimeUnit.MINUTES.toSeconds(duration);
        }
    },
    HOURS {
        @Override
        public long convert(long duration)  {
            return TimeUnit.HOURS.toSeconds(duration);
        }
    },
    DAYS {
        @Override
        public long convert(long duration)  {
            return TimeUnit.DAYS.toSeconds(duration);
        }
    };

    public abstract long convert(long duration);
}
