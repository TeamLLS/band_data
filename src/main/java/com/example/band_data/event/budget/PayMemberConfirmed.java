package com.example.band_data.event.budget;


import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PayMemberConfirmed extends Event {
    private Long payBookId;
    private Long memberId;
    private Integer amount;
    private PayStatus status;
}
