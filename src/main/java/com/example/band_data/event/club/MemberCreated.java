package com.example.band_data.event.club;


import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreated extends Event {

    private Long memberId;
    private String username;
    private Role role;
    private String name;
    private Integer birthYear;
    private String gender;
}
