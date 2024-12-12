package com.example.band_data.event;

import com.example.band_data.event.activity.ActivityCanceled;
import com.example.band_data.event.activity.ActivityClosed;
import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.budget.BudgetUpdated;
import com.example.band_data.event.budget.PayMemberConfirmed;
import com.example.band_data.event.club.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClubCreated.class, name = "ClubCreated"),
        @JsonSubTypes.Type(value = ClubClosed.class, name = "ClubClosed"),
        @JsonSubTypes.Type(value = MemberCreated.class, name = "MemberCreated"),
        @JsonSubTypes.Type(value = MemberLeft.class, name = "MemberLeft"),
        @JsonSubTypes.Type(value = MemberBanned.class, name = "MemberBanned"),
        @JsonSubTypes.Type(value = ActivityClosed.class, name = "ActivityClosed"),
        @JsonSubTypes.Type(value = ActivityCanceled.class, name = "ActivityCanceled"),
        @JsonSubTypes.Type(value = ParticipantConfirmed.class, name = "ParticipantConfirmed"),
        @JsonSubTypes.Type(value = BudgetUpdated.class, name = "BudgetUpdated"),
        @JsonSubTypes.Type(value = PayMemberConfirmed.class, name = "PayMemberConfirmed"),
})
public abstract class Event {

    private String eventId;
    private Long clubId;
    private Long memberId;
    private String triggeredBy;
    private Instant time;

    public Event() {
    }

    public Event(String eventId, Long clubId, Long memberId, String triggeredBy, Instant time) {
        this.eventId = eventId;
        this.clubId = clubId;
        this.memberId = memberId;
        this.triggeredBy = triggeredBy;
        this.time = time;
    }

    public String getEventId() {
        return eventId;
    }

    public Long getClubId() {
        return clubId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }

    public Instant getTime() {
        return time;
    }


}
