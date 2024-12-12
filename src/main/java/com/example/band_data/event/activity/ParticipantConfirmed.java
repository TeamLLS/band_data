package com.example.band_data.event.activity;


import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ParticipantConfirmed extends Event {
    private Long activityId;
    private Long memberId;
    private Instant changedAt;
    private ParticipantStatus status;
}
