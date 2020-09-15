package com.artineer.jaksim.common.date;

import java.time.LocalDateTime;

public enum SupportDateTime {

    SOLAR {
        @Override
        LocalDateTime getMinSupportDateTime() {
            return LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        }

        @Override
        LocalDateTime getMaxSupportDateTime() {
            return LocalDateTime.of(2099, 12, 31, 23, 59, 59);
        }
    };

    abstract LocalDateTime getMinSupportDateTime();

    abstract LocalDateTime getMaxSupportDateTime();
}