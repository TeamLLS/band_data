package com.example.band_data.data.member.domain;

import com.example.band_data.data.club.ClubData;
import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.activity.ParticipantStatus;
import com.example.band_data.event.budget.PayMemberConfirmed;
import com.example.band_data.event.budget.PayStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberData {

    @Id
    @GeneratedValue
    private Long id;

//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clubdata_id")
//    private ClubData clubData;

    private Long clubId;

    @NotNull
    private Long memberId;
    @NotNull
    private Integer date;
    @NotNull
    private Integer point;

    private Integer actAttendCount;
    private Integer actLateAttendCount;
    private Integer actLateNotAttendCount;

    private Integer payCount;
    private Integer unPayCount;
    private Integer latePayCount;

    private Integer payAmount;
    private Integer unPayAmount;
    private Integer latePayAmount;


    public MemberData(Long clubId, Long memberId, Integer date) {
        this.clubId = clubId;
        //this.clubData = clubData;
        this.memberId = memberId;

        this.date = date;
        this.point = 0;

        this.actAttendCount = 0;
        this.actLateAttendCount = 0;
        this.actLateNotAttendCount = 0;

        this.payCount = 0;
        this.unPayCount = 0;
        this.latePayCount = 0;

        this.payAmount = 0;
        this.unPayAmount = 0;
        this.latePayAmount = 0;
    }


    //테스트용
    public MemberData(Long id, Long clubId, Long memberId, Integer date, Integer point, Integer actAttendCount, Integer actLateAttendCount, Integer actLateNotAttendCount, Integer payCount, Integer unPayCount, Integer latePayCount, Integer payAmount, Integer unPayAmount, Integer latePayAmount) {
        this.id = id;
        //this.clubData = clubData;
        this.clubId = clubId;
        this.memberId = memberId;

        this.date = date;
        this.point = point;

        this.actAttendCount = actAttendCount;
        this.actLateAttendCount = actLateAttendCount;
        this.actLateNotAttendCount = actLateNotAttendCount;

        this.payCount = payCount;
        this.unPayCount = unPayCount;
        this.latePayCount = latePayCount;

        this.payAmount = payAmount;
        this.unPayAmount = unPayAmount;
        this.latePayAmount = latePayAmount;
    }


    public void applyParticipantEvent(ParticipantConfirmed event){
        if(event.getStatus() == ParticipantStatus.ATTEND){
            this.actAttendCount++;
            this.point += 1;
        }else if(event.getStatus() == ParticipantStatus.ADDITIONAL_ATTEND){
            this.actLateAttendCount++;
        }else if(event.getStatus() == ParticipantStatus.ADDITIONAL_NOT_ATTEND){
            this.actLateNotAttendCount++;
            this.actAttendCount--;
        }
    }

    public void applyPayMemberEvent(PayMemberConfirmed event){
        if(event.getStatus() == PayStatus.PAID){
            this.payCount++;
            this.payAmount += event.getAmount();
            this.point += 1;
        }else if(event.getStatus() == PayStatus.UNPAID){
            this.unPayCount++;
            this.unPayAmount += event.getAmount();
            this.point -= 1;
        }else if(event.getStatus() == PayStatus.LATE_PAID){
            this.latePayCount++;
            this.unPayCount--;
            this.latePayAmount += event.getAmount();
            this.point += 1;
        }
    }

}
