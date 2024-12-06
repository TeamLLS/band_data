package com.example.band_data.data.member.form;

import com.example.band_data.data.member.domain.MemberSubData;
import com.example.band_data.event.club.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberScoreItem{

    private Long clubId;
    private Long memberId;
    private String username;
    private String memberName;
    private String role;
    private Long point;

    public MemberScoreItem(MemberSubData data,  Long point) {
        this.clubId = data.getClubId();
        this.memberId = data.getMemberId();
        this.username = data.getUsername();
        this.memberName = data.getMemberName();
        this.role = data.getRole().getDisplay();
        this.point = point;
    }
}
