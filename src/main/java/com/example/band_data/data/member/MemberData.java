package com.example.band_data.data.member;


import com.example.band_data.data.club.ClubData;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clubdata_id")
    private ClubData clubData;

    @NotNull
    private Long memberId;
    @NotNull
    private Integer date;

    private Integer actAttendCount;
    private Integer actLateAttendCount;
    private Integer actLateNotAttendCount;

    private Integer payCount;
    private Integer unPayCount;
    private Integer latePayCount;

    private Integer payAmount;
    private Integer unPayAmount;
    private Integer latePayAmount;


    public MemberData(ClubData clubData, Long memberId, Integer date) {
        this.clubData = clubData;
        this.memberId = memberId;
        this.date = date;
    }

    //테스트용
    public MemberData(Long id, ClubData clubData, Long memberId, Integer date, Integer actAttendCount, Integer actLateAttendCount, Integer actLateNotAttendCount, Integer payCount, Integer unPayCount, Integer latePayCount, Integer payAmount, Integer unPayAmount, Integer latePayAmount) {
        this.id = id;
        this.clubData = clubData;
        this.memberId = memberId;
        this.date = date;
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

    public void increaseActAttend(){
        this.actAttendCount++;
    }

    public void increaseActLateAttend(){
        this.actLateAttendCount++;
    }

    public void increaseActLateNotAttend(){
        this.actLateNotAttendCount++;
        this.actAttendCount--;
    }

    public void increasePayCount(int amount){
        this.payCount++;
        this.payAmount += amount;
    }
    public void increaseUnPayCount(int amount){
        this.unPayCount++;
        this.unPayAmount += amount;
    }
    public void increaseLatePayCount(int amount){
        this.latePayCount++;
        this.unPayCount--;
        this.latePayAmount += amount;
    }

}
