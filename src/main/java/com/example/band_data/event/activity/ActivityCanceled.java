package com.example.band_data.event.activity;

import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ActivityCanceled extends Event {
    private Long activityId;
}
