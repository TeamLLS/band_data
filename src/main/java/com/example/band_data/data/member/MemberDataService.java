package com.example.band_data.data.member;


import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.club.ClubDataStore;
import com.example.band_data.data.member.domain.MemberSubData;
import com.example.band_data.data.member.form.*;
import com.example.band_data.data.member.domain.MemberData;
import com.example.band_data.event.Event;
import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.budget.PayMemberConfirmed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberDataService {

    private final ClubDataStore clubDataStore;
    private final MemberDataStore memberDataStore;


    public void recordEvent(Event event){

        Integer date = null;

        if(event instanceof PayMemberConfirmed){
            date = calcTime(((PayMemberConfirmed) event).getPaidAt());
        }else if(event instanceof ParticipantConfirmed){
            date = calcTime(((ParticipantConfirmed) event).getChangedAt());
        }

        MemberData memberData;

        try{
            memberData = memberDataStore.findByMemberId(event.getMemberId(), date);
        }catch (NoSuchElementException e){
            memberData = memberDataStore.save(new MemberData(event.getClubId(), event.getMemberId(), date));
        }

        if(event instanceof ParticipantConfirmed){
            memberData.applyParticipantEvent((ParticipantConfirmed) event);
            memberDataStore.findSubByMemberId(event.getMemberId()).updateAttendData((ParticipantConfirmed) event);
        }else if(event instanceof PayMemberConfirmed){
            memberData.applyPayMemberEvent((PayMemberConfirmed) event);
            memberDataStore.findSubByMemberId(event.getMemberId()).updatePayData((PayMemberConfirmed) event);
        }
    }

    public List<ParticipantDataItem> getParticipantTrend(Long clubId, Long memberId, Instant fromTime){

        Instant toTime = Instant.now();
        if(fromTime==null){
            fromTime = toTime.atZone(ZoneOffset.UTC).minus(6, ChronoUnit.MONTHS).toInstant();
        }

        Integer fromDate = calcTime(fromTime);
        Integer toDate = calcTime(toTime);

        List<ClubData> clubList = clubDataStore.findListByDate(clubId, fromDate, toDate);
        List<MemberData> memberList = memberDataStore.findListByData(memberId, fromDate, toDate);
        List<ParticipantDataItem> result = new ArrayList<>();


        int clubSize=clubList.size();
        ClubData clubData;

        int memberIdx=0;
        int memberSize=memberList.size();
        MemberData memberData;


        for (int i=0; i<clubSize; i++) {

            clubData = clubList.get(i);
            memberData = memberIdx<memberSize?memberList.get(memberIdx):null;

            if (memberData!=null && clubData.getDate().equals(memberData.getDate())){
                result.add(new ParticipantDataItem(memberData, clubData.getActCloseCount()));
                memberIdx++;
            }else{
                result.add(new ParticipantDataItem(clubId, memberId, clubData.getDate(), clubData.getActCloseCount()));
            }
        }

        return result;
    }

    public List<PayMemberDataItem> getPayMemberTrend(Long clubId, Long memberId, Instant fromTime){

        Instant toTime = Instant.now();
        if(fromTime==null){
            fromTime = toTime.atZone(ZoneOffset.UTC).minus(6, ChronoUnit.MONTHS).toInstant();
        }

        Integer fromDate = calcTime(fromTime);
        Integer toDate = calcTime(toTime);

        List<MemberData> list = memberDataStore.findListByData(memberId, fromDate, toDate);
        List<PayMemberDataItem> result = new ArrayList<>();

        for (MemberData data : list) {
            result.add(new PayMemberDataItem(data));
        }

        return result;
    }

    public MemberScore getMemberScore(Long clubId, Long memberId){
        Instant toTime = Instant.now();
        Instant fromTime = toTime.atZone(ZoneOffset.UTC).minus(3, ChronoUnit.MONTHS).toInstant();

        Integer toDate = calcTime(toTime);
        Integer fromDate = calcTime(fromTime);

        MemberSubData subData = memberDataStore.findSubByMemberId(memberId);
        MemberDataDto data = memberDataStore.findSumByMemberId(memberId, fromDate, toDate);

        if(data.getMemberId()==null){
            data = new MemberDataDto(clubId, memberId);
        }
        Long actTotal = clubDataStore.findActCountByDate(clubId, fromDate, toDate);

        double attendRate = actTotal>0?(double)(data.getAttendCount() + data.getLateAttendCount())/actTotal:1.0;

        double payRate = (data.getPayCount() + data.getLatePayCount() + data.getUnPayCount())>0?
                (double)(data.getPayCount() + data.getLatePayCount())/(data.getPayCount() + data.getLatePayCount() + data.getUnPayCount()): 1.0;

        long payAmount = data.getPayAmount() + data.getLatePayAmount();

        return new MemberScore(clubId, memberId, calcPoint(subData, data, actTotal),
                attendRate, payRate, payAmount, subData.getUnpaidTotal(), subData.getLastAttend(), subData.getLastPay());
    }

    public List<MemberScoreItem> getMemberScores(Long clubId, Integer option){
        Instant toTime = Instant.now();
        Instant fromTime = toTime.atZone(ZoneOffset.UTC).minus(3, ChronoUnit.MONTHS).toInstant();

        Integer toDate = calcTime(toTime);
        Integer fromDate = calcTime(fromTime);

        List<MemberSubData> subList = memberDataStore.findSubListByClubId(clubId);
        List<MemberDataDto> list = memberDataStore.findSumListByClubId(clubId, fromDate, toDate);
        Long actTotal = clubDataStore.findActCountByDate(clubId, fromDate, toDate);

        Map<Long, MemberDataDto> map = new HashMap<>();

        for (MemberDataDto memberDataDto : list) {
            map.put(memberDataDto.getMemberId(), memberDataDto);
        }

        List<MemberScoreItem> result = new ArrayList<>();

        long point = 0;
        long totalPoint = 0;
        MemberDataDto data;

        for (MemberSubData subData : subList) {

            data = map.get(subData.getMemberId());
            if(data==null){
                data=new MemberDataDto(clubId, subData.getMemberId());
            }

            point = calcPoint(subData, data, actTotal);
            totalPoint +=point;

            result.add(new MemberScoreItem(subData, point));
        }

        result.sort((m1, m2) -> Long.compare(m2.getPoint(), m1.getPoint()));

        if(option==null){
            option=0;
        }

        if(option==1){
            result.sort((m1, m2) -> Long.compare(m2.getPoint(), m1.getPoint()));
            result = result.subList(0, 3);
        }else if(option==2){
            int avg = (int)totalPoint/result.size();

            System.out.println(avg);
            System.out.println((int)(avg*0.3));

            result = result.stream().filter(m -> m.getPoint() < (int)(avg*0.3)).collect(Collectors.toList());
            result.sort((m1, m2) -> Long.compare(m2.getPoint(), m1.getPoint()));
        }

        return result;
    }


    private Integer calcTime(Instant time){
        ZonedDateTime zonedDateTime = time.atZone(ZoneOffset.UTC);
        int year = zonedDateTime.getYear();
        int month = zonedDateTime.getMonthValue();

        return year*100 + month;
    }

    private Long calcPoint(MemberSubData subData, MemberDataDto data, Long actTotal){

        double attendRate = actTotal>0?(double)(data.getAttendCount() + data.getLateAttendCount())/actTotal:1.0;

        double payCountRate = (data.getPayCount() + data.getLatePayCount() + data.getUnPayCount())>0?
                (double)(data.getPayCount() + data.getLatePayCount())/(data.getPayCount() + data.getLatePayCount() + data.getUnPayCount()): 1.0;

        double payAmountRate = (data.getPayAmount() + data.getLatePayAmount()+ data.getUnPayAmount())>0?
                (double)(data.getPayAmount() + data.getLatePayAmount())/(data.getPayAmount() + data.getLatePayAmount() + data.getUnPayAmount()): 1.0;

        long result = 0;

        result += data.getPoint();

        if(attendRate<0.3){
            result -=10;
        }else if(attendRate>0.9){
            result +=20;
        }else if(attendRate>0.6){
            result +=10;
        }

        if(payCountRate<0.3){
            result -=20;
        }else if(payCountRate<0.6){
            result -=10;
        }else if(payCountRate>0.9){
            result +=10;
        }

        if(payAmountRate<0.3){
            result -=20;
        }else if(payAmountRate<0.6){
            result -=10;
        }else if(payAmountRate>0.9){
            result +=10;
        }

        int lastAttendDate = calcTime(subData.getLastAttend());
        int lastPayDate = calcTime(subData.getLastPay());
        int date = calcTime(Instant.now());

        int monthTerm = (date/100 - lastAttendDate/100)*12;
        result -= ((date%100+monthTerm) - lastAttendDate%100)* 10L;

        monthTerm = (date/100 - lastPayDate/100)*12;
        result -= ((date%100+monthTerm) - lastPayDate%100)* 10L;

        return result;
    }
}
