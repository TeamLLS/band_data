package com.example.band_data.data.club.form;

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
    private Integer period;

    private Integer trend;

    private Integer income;
    private Integer expense;


    public BudgetDataItem(Long clubId, Integer year, Integer month, Integer period, Integer trend, Integer income, Integer expense) {
        this.clubId = clubId;
        this.year = year;
        this.month = month;
        this.period = period;
        this.trend = trend;
        this.income = income;
        this.expense = expense;
    }


}
