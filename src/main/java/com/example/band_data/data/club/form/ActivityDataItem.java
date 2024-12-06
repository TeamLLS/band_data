package com.example.band_data.data.club.form;

import com.example.band_data.data.club.ClubData;
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

    private Integer trend;

    private Integer actCloseCount;
    private Integer actCancelCount;

    public ActivityDataItem(ClubData data) {
        this.clubId = data.getClubId();
        this.year = data.getDate()/100;
        this.month = data.getDate()%100;
        this.actCloseCount = data.getActCloseCount();
        this.actCancelCount = data.getActCancelCount();
        this.trend = actCloseCount;
    }

    public ActivityDataItem(Long clubId, Integer date){
        this.clubId = clubId;
        this.year = date/100;
        this.month = date%100;
        this.actCloseCount = 0;
        this.actCancelCount = 0;
        this.trend = 0;
    }
}
