package com.example.band_data.data.member.form;

import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.member.domain.MemberData;
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
    //private Integer period;

    private Double trend;

    private Integer attendCount;
    private Integer notAttendCount;
    private Integer lateAttendCount;
    private Integer lateNotAttendCount;

    public ParticipantDataItem(MemberData data, Integer actCount) {
        this.clubId = data.getClubId();
        this.memberId = data.getMemberId();
        this.year = data.getDate()/100;
        this.month = data.getDate()%100;
        this.attendCount = data.getActAttendCount();
        this.lateAttendCount = data.getActLateAttendCount();
        this.lateNotAttendCount = data.getActLateNotAttendCount();
        this.notAttendCount = actCount - (attendCount + lateAttendCount + lateNotAttendCount);
        this.trend = actCount>0?(double) (attendCount + lateAttendCount) / actCount:1.0;
    }

    public ParticipantDataItem(Long clubId, Long memberId, Integer date, Integer actCount){
        this.clubId = clubId;
        this.memberId = memberId;
        this.year = date/100;
        this.month = date%100;
        this.attendCount = 0;
        this.lateAttendCount = 0;
        this.lateNotAttendCount = 0;
        this.notAttendCount = actCount;
        this.trend = actCount>0?0.0:1.0;
    }


    /*
    public ParticipantDataItem(MemberDataItem data){
        this.clubId = data.getClubId();
        this.memberId = data.getMemberId();
        this.year = data.getDate() / 100;
        this.month = data.getDate() % 100;
        this.period = data.getPeriod();
        this.attendCount = data.getAttendCount();
        this.lateAttendCount = data.getLateAttendCount();
        this.lateNotAttendCount = data.getLateNotAttendCount();
        this.notAttendCount = data.getTotalActCount() - (attendCount + lateAttendCount + lateNotAttendCount);
        this.trend = (double) (attendCount + lateAttendCount) / data.getAttendCount();
    }
    */
}
