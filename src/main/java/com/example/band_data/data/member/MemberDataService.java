package com.example.band_data.data.member;


import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.club.ClubDataStore;
import com.example.band_data.data.member.form.ParticipantDataItem;
import com.example.band_data.data.member.form.PayMemberDataItem;
import com.example.band_data.event.Event;
import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.activity.ParticipantStatus;
import com.example.band_data.event.budget.PayMemberConfirmed;
import com.example.band_data.event.budget.PayStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDataService {

    private final ClubDataStore clubDataStore;
    private final MemberDataStore memberDataStore;


    public void recordEvent(Event event){
        Integer date = calcTime(event.getTime());
        MemberData memberData;

        try{
            memberData = memberDataStore.findByMemberId(event.getMemberId(), date);
        }catch (NoSuchElementException e){
            ClubData clubData = clubDataStore.findByDate(event.getClubId(), date);
            memberData = memberDataStore.save(new MemberData(clubData, event.getMemberId(), date));
        }

        if(event instanceof ParticipantConfirmed && ((ParticipantConfirmed) event).getStatus() == ParticipantStatus.ATTEND){
            memberData.increaseActAttend();
        }else if(event instanceof ParticipantConfirmed && ((ParticipantConfirmed) event).getStatus() == ParticipantStatus.ADDITIONAL_ATTEND){
            memberData.increaseActLateAttend();
        }else if(event instanceof ParticipantConfirmed && ((ParticipantConfirmed) event).getStatus() == ParticipantStatus.ADDITIONAL_NOT_ATTEND){
            memberData.increaseActLateNotAttend();
        }else if(event instanceof PayMemberConfirmed && ((PayMemberConfirmed) event).getStatus() == PayStatus.PAID){
            memberData.increasePayCount(((PayMemberConfirmed) event).getAmount());
        }else if(event instanceof PayMemberConfirmed && ((PayMemberConfirmed) event).getStatus() == PayStatus.UNPAID){
            memberData.increaseUnPayCount(((PayMemberConfirmed) event).getAmount());
        }else if(event instanceof PayMemberConfirmed && ((PayMemberConfirmed) event).getStatus() == PayStatus.LATE_PAID){
            memberData.increaseLatePayCount(((PayMemberConfirmed) event).getAmount());
        }
    }

    public List<ParticipantDataItem> getParticipantTrend(Long memberId, Integer period, int pageNo){
        if(period==null){
            period=1;
        }

        Integer date = calcTime(Instant.now());
        List<MemberData> list = memberDataStore.findListWithClubData(memberId, date, period, pageNo, 2).getContent();
        List<ParticipantDataItem> result = new ArrayList<>();

        int p = 0;
        int attendCount = 0;
        int notAttendCount = 0;
        int lateAttendCount = 0;
        int lateNotAttendCount = 0;
        int totalActCount = 0;

        int year = 0;
        int month = 0;
        double trend = 0;

        for (MemberData data : list) {
            p++;
            totalActCount += data.getClubData().getActCloseCount();
            attendCount += data.getActAttendCount();
            lateAttendCount += data.getActLateAttendCount();
            lateNotAttendCount += data.getActLateNotAttendCount();

            year = data.getDate()/100;
            month = data.getDate()%100;

            if(p==period){
                notAttendCount = totalActCount - (attendCount + lateAttendCount + lateNotAttendCount);
                trend = (double) (attendCount + lateAttendCount) / totalActCount;

                result.add(new ParticipantDataItem(data.getClubData().getClubId(), memberId, year, month, period,
                        trend, attendCount, notAttendCount, lateAttendCount, lateNotAttendCount));

                p=0;
                attendCount = 0;
                lateAttendCount = 0;
                lateNotAttendCount = 0;
                totalActCount = 0;
            }
        }

        if(p>0){
            notAttendCount = totalActCount - (attendCount + lateAttendCount + lateNotAttendCount);
            trend = (double) (attendCount + lateAttendCount) / totalActCount;

            result.add(new ParticipantDataItem(list.get(0).getClubData().getClubId(), memberId, year, month, p,
                    trend, attendCount, notAttendCount, lateAttendCount, lateNotAttendCount));
        }

        return result;
    }

    public List<PayMemberDataItem> getPayMemberTrend(Long memberId, Integer period, int pageNo){
        if(period==null){
            period=1;
        }

        Integer date = calcTime(Instant.now());
        List<MemberData> list = memberDataStore.findListWithClubData(memberId, date, period, pageNo, 2).getContent();
        List<PayMemberDataItem> result = new ArrayList<>();

        int p = 0;
        int payCount = 0;
        int unPayCount = 0;
        int latePayCount = 0;

        int payAmount = 0;
        int unPayAmount = 0;
        int latePayAmount = 0;

        int year = 0;
        int month = 0;
        double countTrend=0;
        double amountTrend=0;

        for (MemberData data : list) {
            p++;
            payCount += data.getPayCount();
            unPayCount += data.getUnPayCount();
            latePayCount += data.getLatePayCount();

            payAmount += data.getPayAmount();
            unPayAmount += data.getUnPayAmount();
            latePayAmount += data.getLatePayAmount();


            year = data.getDate()/100;
            month = data.getDate()%100;

            if(p==period){
                countTrend = (double) (payCount + latePayCount) / (payCount + unPayCount + latePayCount);
                amountTrend = (double) (payAmount + latePayAmount) / (payAmount + unPayAmount + latePayAmount);

                result.add(new PayMemberDataItem(data.getClubData().getClubId(), memberId, year, month, period,
                        countTrend, amountTrend, payCount, unPayCount, latePayCount, payAmount, unPayAmount, latePayAmount));

                p=0;
                payCount = 0;
                unPayCount = 0;
                latePayCount = 0;

                payAmount = 0;
                unPayAmount = 0;
                latePayAmount = 0;
            }
        }

        if(p>0){
            countTrend = (double) (payCount + latePayCount) / (payCount + unPayCount + latePayCount);
            amountTrend = (double) (payAmount + latePayAmount) / (payAmount + unPayAmount + latePayAmount);

            result.add(new PayMemberDataItem(list.get(0).getClubData().getClubId(), memberId, year, month, period,
                    countTrend, amountTrend, payCount, unPayCount, latePayCount, payAmount, unPayAmount, latePayAmount));
        }

        return result;
    }

    public Integer calcTime(Instant time){
        ZonedDateTime zonedDateTime = time.atZone(ZoneOffset.UTC);
        int year = zonedDateTime.getYear();
        int month = zonedDateTime.getMonthValue();

        return year*100 + month;
    }


}
