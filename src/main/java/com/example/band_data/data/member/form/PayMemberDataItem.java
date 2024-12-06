package com.example.band_data.data.member.form;

import com.example.band_data.data.member.domain.MemberData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayMemberDataItem {
    private Long clubId;
    private Long memberId;
    private Integer year;
    private Integer month;
    //private Integer period;

    private Double trend;

    private Integer payCount;
    private Integer unPayCount;
    private Integer latePayCount;

    private Integer payAmount;
    private Integer unPayAmount;
    private Integer latePayAmount;

    public PayMemberDataItem(MemberData data) {
        this.clubId = data.getClubId();
        this.memberId = data.getMemberId();
        this.year = data.getDate()/100;
        this.month = data.getDate()%100;
        this.payCount = data.getPayCount();
        this.unPayCount = data.getUnPayCount();
        this.latePayCount = data.getLatePayCount();
        this.payAmount = data.getPayAmount();
        this.unPayAmount = data.getUnPayAmount();
        this.latePayAmount = data.getLatePayAmount();
        this.trend = (payCount + unPayCount + latePayCount)>0?(double) (payCount + latePayCount) / (payCount + unPayCount + latePayCount):1.0;
    }

    /*
    public PayMemberDataItem(MemberDataItem data){
        this.clubId = data.getClubId();
        this.memberId = data.getMemberId();
        this.year = data.getDate() / 100;
        this.month = data.getDate() % 100;
        this.period = data.getPeriod();
        this.payCount = data.getPayCount();
        this.unPayCount = data.getUnPayCount();
        this.latePayCount = data.getLatePayCount();
        this.payAmount = data.getPayAmount();
        this.unPayAmount = data.getUnPayAmount();
        this.latePayAmount = data.getLatePayAmount();
        this.trend = (double) (payCount + latePayCount) / (payCount + unPayCount + latePayCount);
    }
    */

}
