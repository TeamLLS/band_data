package com.example.band_data.data.club.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDataItem {
    private Long clubId;
    private Integer year;
    private Integer month;
    private Integer period;

    private Integer trend;

    private Integer memberRegisterCount;
    private Integer memberLeftCount;
    private Integer memberBanCount;

    public MemberDataItem(Long clubId, Integer year, Integer month, Integer period, Integer trend, Integer memberRegisterCount, Integer memberLeftCount, Integer memberBanCount) {
        this.clubId = clubId;
        this.year = year;
        this.month = month;
        this.period = period;
        this.trend = trend;
        this.memberRegisterCount = memberRegisterCount;
        this.memberLeftCount = memberLeftCount;
        this.memberBanCount = memberBanCount;
    }
}
