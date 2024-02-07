package com.intuit.bookmyshow.request;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduleRequest {
    public UUID movieId;
    public UUID screenId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
}
