package com.example.band_data.data.club.form;

import com.example.band_data.data.club.ClubData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetDataItem {
    private Long clubId;
    private Integer year;
    private Integer month;

    private Integer trend;
    private Integer income;
    private Integer expense;


    public BudgetDataItem(ClubData clubData) {
        this.clubId = clubData.getClubId();
        this.year = clubData.getDate()/100;
        this.month = clubData.getDate()%100;
        this.income = clubData.getIncome();
        this.expense = clubData.getExpense();
        this.trend = income + expense;
    }
    public BudgetDataItem(Long clubId, Integer date){
        this.clubId = clubId;
        this.year = date/100;
        this.month = date%100;
        this.income = 0;
        this.expense = 0;
        this.trend = 0;
    }

}
