package com.example.band_data.data.club.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityDataItem {
    private Long clubId;
    private Integer year;
    private Integer month;
    private Integer period;

    private Integer trend;

    private Integer actCloseCount;
    private Integer actCancelCount;

    public ActivityDataItem(Long clubId, Integer year, Integer month, Integer period, Integer trend, Integer actCloseCount, Integer actCancelCount) {
        this.clubId = clubId;
        this.year = year;
        this.month = month;
        this.period = period;
        this.trend = trend;
        this.actCloseCount = actCloseCount;
        this.actCancelCount = actCancelCount;
    }

}
