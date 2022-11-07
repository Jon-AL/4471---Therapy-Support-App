package com.appUtilities;

import java.time.LocalDateTime;

public class makeTime {

    public LocalDateTime makeTime(int year, int month, int day, int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute);
    }

}
