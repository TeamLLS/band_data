package com.example.band_data.data.member.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberDataDto {
    private Long clubId;
    private Long memberId;
//    private Integer date;
//    private Integer period;
    private Long point;

    private Long attendCount;
    private Long lateAttendCount;
    private Long lateNotAttendCount;

    private Long payCount;
    private Long unPayCount;
    private Long latePayCount;

    private Long payAmount;
    private Long unPayAmount;
    private Long latePayAmount;

    public MemberDataDto(Long clubId, Long memberId/*, Integer date, Object period*/, Long point, Long attendCount, Long lateAttendCount, Long lateNotAttendCount, Long payCount, Long unPayCount, Long latePayCount, Long payAmount, Long unPayAmount, Long latePayAmount) {
        this.clubId = clubId;
        this.memberId = memberId;
//        this.date = date;
//        this.period = (Integer) period;
        this.point = point;
        this.attendCount = attendCount;
        this.lateAttendCount = lateAttendCount;
        this.lateNotAttendCount = lateNotAttendCount;
        this.payCount = payCount;
        this.unPayCount = unPayCount;
        this.latePayCount = latePayCount;
        this.payAmount = payAmount;
        this.unPayAmount = unPayAmount;
        this.latePayAmount = latePayAmount;
    }
    public MemberDataDto(Long clubId, Long memberId) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.point = 0L;
        this.attendCount = 0L;
        this.lateAttendCount = 0L;
        this.lateNotAttendCount = 0L;
        this.payCount = 0L;
        this.unPayCount = 0L;
        this.latePayCount = 0L;
        this.payAmount = 0L;
        this.unPayAmount = 0L;
        this.latePayAmount = 0L;
    }
}
