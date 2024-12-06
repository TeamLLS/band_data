package com.example.band_data.data.member.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
public class MemberScore {

    private Long clubId;
    private Long memberId;

    private Long point;
    private Double attendRate;
    private Double payRate;
    private Long payAmount;

    private Integer unPaidTotal;
    private Instant lastAttendTime;
    private Instant lastPayTime;

    public MemberScore(Long clubId, Long memberId, Long point, Double attendRate, Double payRate, Long payAmount, Integer unPaidTotal, Instant lastAttendTime, Instant lastPayTime) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.point = point;
        this.attendRate = attendRate;
        this.payRate = payRate;
        this.payAmount = payAmount;
        this.unPaidTotal = unPaidTotal;
        this.lastAttendTime = lastAttendTime;
        this.lastPayTime = lastPayTime;
    }
}
