package com.example.band_data.event.club;

import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberLeft extends Event {
    private Long memberId;
}
