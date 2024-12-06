package com.example.band_data.data.club.form;

import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.member.domain.MemberData;
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
    //private Integer period;

    private Integer trend;

    private Integer memberRegisterCount;
    private Integer memberLeftCount;
    private Integer memberBanCount;

    public MemberDataItem(ClubData data) {
        this.clubId = data.getClubId();
        this.year = data.getDate()/100;
        this.month = data.getDate()%100;
        this.memberRegisterCount = data.getMemberRegisterCount();
        this.memberLeftCount = data.getMemberLeftCount();
        this.memberBanCount = data.getMemberBanCount();
        this.trend = memberRegisterCount - (memberLeftCount + memberBanCount);
    }


    public MemberDataItem(Long clubId, Integer date){
        this.clubId = clubId;
        this.year = date/100;
        this.month = date%100;
        this.memberRegisterCount = 0;
        this.memberLeftCount = 0;
        this.memberBanCount = 0;
        this.trend = 0;
    }

}
