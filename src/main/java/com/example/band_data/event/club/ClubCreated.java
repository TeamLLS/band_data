package com.example.band_data.event.club;


import com.example.band_data.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClubCreated extends Event {
    private String name;

    private String image;

    private String description;

    private String contactInfo;

}
