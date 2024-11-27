package com.example.band_data.data.member.form;

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
    private Integer period;

    private Double countTrend;
    private Double amountTrend;

    private Integer payCount;
    private Integer unPayCount;
    private Integer latePayCount;

    private Integer payAmount;
    private Integer unPayAmount;
    private Integer latePayAmount;


    public PayMemberDataItem(Long clubId, Long memberId, Integer year, Integer month, Integer period,
                             Double countTrend, Double amountTrend,
                             Integer payCount, Integer unPayCount, Integer latePayCount, Integer payAmount, Integer unPayAmount, Integer latePayAmount) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.year = year;
        this.month = month;
        this.period = period;
        this.countTrend = countTrend;
        this.amountTrend = amountTrend;
        this.payCount = payCount;
        this.unPayCount = unPayCount;
        this.latePayCount = latePayCount;
        this.payAmount = payAmount;
        this.unPayAmount = unPayAmount;
        this.latePayAmount = latePayAmount;
    }
}
