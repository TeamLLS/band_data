package com.example.band_data.data.club;

import com.example.band_data.data.club.form.ActivityDataItem;
import com.example.band_data.data.club.form.BudgetDataItem;
import com.example.band_data.data.club.form.MemberDataItem;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubDataService {

    private final ClubDataStore clubDataStore;

    public void recordEvent(Event event){
        Integer date = calcTime(event.getTime());
        ClubData clubData;

        try{
            clubData = clubDataStore.findByDate(event.getClubId(), date);
        }catch (NoSuchElementException e){
            clubData = clubDataStore.save(new ClubData(event.getClubId(), date));
        }

        if(event instanceof ActivityClosed){
            clubData.increaseActClose();
        }else if(event instanceof ActivityCanceled){
            clubData.increaseActCancel();
        }else if(event instanceof MemberCreated){
            clubData.increaseMemRegister();
        }else if(event instanceof MemberLeft){
            clubData.increaseMemLeft();
        }else if(event instanceof MemberBanned){
            clubData.increaseMemBan();
        }else if(event instanceof BudgetUpdated && ((BudgetUpdated) event).getAmount()>0){;
            clubData.increaseIncome(((BudgetUpdated) event).getAmount());
        }else if(event instanceof BudgetUpdated && ((BudgetUpdated) event).getAmount()<0){
            clubData.increaseExpense(((BudgetUpdated)event).getAmount());
        }
    }


    public List<MemberDataItem> getMemberTrend(Long clubId, Integer period, int pageNo){
        if(period==null){
            period=1;
        }

        Integer date = calcTime(Instant.now());
        List<ClubData> list = clubDataStore.findList(clubId, date, period, pageNo, 2).getContent();
        List<MemberDataItem> result = new ArrayList<>();

        int p = 0;
        int memberRegisterCount = 0;
        int memberLeftCount = 0;
        int memberBanCount = 0;

        int year = 0;
        int fromMonth = 0;
        int trend = 0;

        for (ClubData data : list) {
            p++;
            memberRegisterCount += data.getMemberRegisterCount();
            memberLeftCount += data.getMemberLeftCount();
            memberBanCount += data.getMemberBanCount();

            year = data.getDate()/100;
            fromMonth = data.getDate()%100;

            if(p==period){
                trend = memberRegisterCount - (memberLeftCount + memberBanCount);

                result.add(new MemberDataItem(clubId, year, fromMonth, period, trend,
                        memberRegisterCount, memberLeftCount, memberBanCount));

                p=0;
                memberRegisterCount=0;
                memberLeftCount=0;
                memberBanCount=0;
            }
        }

        if(p>0){
            trend = memberRegisterCount - (memberLeftCount + memberBanCount);

            result.add(new MemberDataItem(clubId, year, fromMonth, p, trend,
                    memberRegisterCount, memberLeftCount, memberBanCount));
        }

        return result;
    }


    public List<ActivityDataItem> getActivityTrend(Long clubId, Integer period, int pageNo){
        if(period==null){
            period=1;
        }

        Integer date = calcTime(Instant.now());
        List<ClubData> list = clubDataStore.findList(clubId, date, period, pageNo, 2).getContent();
        List<ActivityDataItem> result = new ArrayList<>();

        int p = 0;
        int actCloseCount = 0;
        int actCancelCount = 0;

        int year = 0;
        int month = 0;
        int trend = 0;

        for (ClubData data : list) {
            p++;
            actCloseCount += data.getActCloseCount();
            actCancelCount += data.getActCancelCount();

            year = data.getDate()/100;
            month = data.getDate()%100;

            if(p==period){
                trend = actCloseCount;

                result.add(new ActivityDataItem(clubId, year, month, period, trend, actCloseCount, actCancelCount));

                p=0;
                actCloseCount=0;
                actCancelCount=0;
            }
        }

        if(p>0){
            trend = actCloseCount;
            result.add(new ActivityDataItem(clubId, year, month, p, trend, actCloseCount, actCancelCount));
        }


        return result;
    }

    public List<BudgetDataItem> getBudgetTrend(Long clubId, Integer period, int pageNo){
        if(period==null){
            period=1;
        }

        Integer date = calcTime(Instant.now());
        List<ClubData> list = clubDataStore.findList(clubId, date, period, pageNo, 2).getContent();
        List<BudgetDataItem> result = new ArrayList<>();

        int p = 0;
        int income = 0;
        int expense = 0;

        int year = 0;
        int month = 0;
        int trend = 0;

        for (ClubData data : list) {
            p++;
            income += data.getIncome();
            expense += data.getExpense();

            year = data.getDate()/100;
            month = data.getDate()%100;

            if(p==period){
                trend = income - expense;

                result.add(new BudgetDataItem(clubId, year, month, period, trend, income, expense));

                p=0;
                income=0;
                expense=0;
            }
        }

        if(p>0){
            trend = income - expense;

            result.add(new BudgetDataItem(clubId, year, month, p, trend, income, expense));
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
