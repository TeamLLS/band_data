package com.example.band_data.data.member.domain;

import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.activity.ParticipantStatus;
import com.example.band_data.event.budget.PayMemberConfirmed;
import com.example.band_data.event.budget.PayStatus;
import com.example.band_data.event.club.MemberBanned;
import com.example.band_data.event.club.MemberCreated;
import com.example.band_data.event.club.MemberLeft;
import com.example.band_data.event.club.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Getter
@NoArgsConstructor
public class MemberSubData {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    private Long clubId;
    @NotNull
    private Long memberId;
    @NotNull
    private String username;
    private String memberName;
    private Role role;
    private Instant lastAttend;
    private Instant lastPay;
    private Integer unpaidTotal;
    private Boolean active;


    public MemberSubData(MemberCreated event) {
        this.clubId = event.getClubId();
        this.memberId = event.getMemberId();
        this.memberName = event.getName();
        this.username = event.getUsername();
        this.role = event.getRole();
        this.lastAttend = event.getTime();
        this.lastPay = event.getTime();
        this.unpaidTotal = 0;
        this.active = true;
    }

    //테스트용
    public MemberSubData(Long clubId, Long memberId, String username, String memberName, Role role, Instant lastAttend, Instant lastPay, Integer unpaidTotal) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.username = username;
        this.memberName = memberName;
        this.role = role;
        this.lastAttend = lastAttend;
        this.lastPay = lastPay;
        this.unpaidTotal = unpaidTotal;
        this.active = true;
    }

    public void applyBanEvent(MemberBanned event){
        this.active = false;
    }

    public void applyLeftEvent(MemberLeft event){
        this.active = false;
    }

    public void updateAttendData(ParticipantConfirmed event){
        if(event.getStatus()== ParticipantStatus.ATTEND || event.getStatus()==ParticipantStatus.ADDITIONAL_ATTEND){
            this.lastAttend = event.getTime();
        }
    }

    public void updatePayData(PayMemberConfirmed event){
        if(event.getStatus()==PayStatus.PAID || event.getStatus()==PayStatus.LATE_PAID) {
            this.lastAttend = event.getTime();
        }else{
            this.unpaidTotal += event.getAmount();
        }
    }

}
