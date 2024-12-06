package com.example.band_data.data.club;

import com.example.band_data.data.club.form.ActivityDataItem;
import com.example.band_data.data.club.form.BudgetDataItem;
import com.example.band_data.data.club.form.MemberDataItem;
import com.example.band_data.data.member.MemberDataStore;
import com.example.band_data.data.member.domain.MemberSubData;
import com.example.band_data.event.Event;
import com.example.band_data.event.activity.ActivityCanceled;
import com.example.band_data.event.activity.ActivityClosed;
import com.example.band_data.event.budget.BudgetUpdated;
import com.example.band_data.event.club.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubDataService {

    private final ClubDataStore clubDataStore;
    private final MemberDataStore memberDataStore;

    public void recordEvent(Event event){
        Integer date = calcTime(event.getTime());
        ClubData clubData;

        try{
            clubData = clubDataStore.findByDate(event.getClubId(), date);
        }catch (NoSuchElementException e){
            clubData = clubDataStore.save(new ClubData(event.getClubId(), date));
        }

        if(event instanceof ActivityClosed){
            clubData.applyActivityClosedEvent((ActivityClosed) event);
        }else if(event instanceof ActivityCanceled){
            clubData.applyActivityCanceledEvent((ActivityCanceled) event);
        }else if(event instanceof MemberCreated){
            clubData.applyMemberCreatedEvent((MemberCreated) event);
            memberDataStore.saveSub(new MemberSubData((MemberCreated) event));
        }else if(event instanceof MemberLeft){
            clubData.applyMemberLeftEvent((MemberLeft) event);
            memberDataStore.findSubByMemberId(event.getMemberId()).applyLeftEvent((MemberLeft) event);
        }else if(event instanceof MemberBanned){
            clubData.applyMemberBannedEvent((MemberBanned) event);
            memberDataStore.findSubByMemberId(event.getMemberId()).applyBanEvent((MemberBanned) event);
        }else if(event instanceof BudgetUpdated) {
            clubData.applyBudgetEvent((BudgetUpdated) event);
        }
    }


    public List<MemberDataItem> getMemberTrend(Long clubId, Instant fromTime){

        Instant toTime = Instant.now();

        if(fromTime==null){
            fromTime = toTime.minus(6, ChronoUnit.MONTHS);
        }

        Integer fromDate = calcTime(fromTime);
        Integer toDate = calcTime(toTime);

        List<ClubData> list = clubDataStore.findListByDate(clubId, fromDate, toDate);
        List<MemberDataItem> result = new ArrayList<>();

        for (ClubData data : list) {
            result.add(new MemberDataItem(data));
        }

        return result;
    }


    public List<ActivityDataItem> getActivityTrend(Long clubId, Instant fromTime){
        Instant toTime = Instant.now();

        if(fromTime==null){
            fromTime = toTime.minus(6, ChronoUnit.MONTHS);
        }

        Integer fromDate = calcTime(fromTime);
        Integer toDate = calcTime(toTime);

        List<ClubData> list = clubDataStore.findListByDate(clubId, fromDate, toDate);
        List<ActivityDataItem> result = new ArrayList<>();

        for (ClubData data : list) {
            result.add(new ActivityDataItem(data));
        }

        return result;
    }

    public List<BudgetDataItem> getBudgetTrend(Long clubId, Instant fromTime){
        Instant toTime = Instant.now();

        if(fromTime==null){
            fromTime = toTime.minus(6, ChronoUnit.MONTHS);
        }

        Integer fromDate = calcTime(fromTime);
        Integer toDate = calcTime(toTime);

        List<ClubData> list = clubDataStore.findListByDate(clubId, fromDate, toDate);
        List<BudgetDataItem> result = new ArrayList<>();

        for (ClubData data : list) {
            result.add(new BudgetDataItem(data));
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
