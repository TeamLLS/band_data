package com.example.band_data.data.member.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDataItem {
    private Long clubId;
    private Long memberId;
    private Integer year;
    private Integer month;
    private Integer period;

    private Double trend;

    private Integer attendCount;
    private Integer notAttendCount;
    private Integer lateAttendCount;
    private Integer lateNotAttendCount;


    public ParticipantDataItem(Long clubId, Long memberId, Integer year, Integer month, Integer period,
                               Double trend, Integer attendCount, Integer notAttendCount, Integer lateAttendCount, Integer lateNotAttendCount) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.year = year;
        this.month = month;
        this.period = period;
        this.trend = trend;
        this.attendCount = attendCount;
        this.notAttendCount = notAttendCount;
        this.lateAttendCount = lateAttendCount;
        this.lateNotAttendCount = lateNotAttendCount;
    }
}
