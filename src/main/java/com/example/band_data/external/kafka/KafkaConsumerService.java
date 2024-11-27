package com.example.band_data.external.kafka;

import com.example.band_data.event.Event;
import com.example.band_data.event.UnknownEventException;
import com.example.band_data.event.activity.ActivityCanceled;
import com.example.band_data.event.activity.ActivityClosed;
import com.example.band_data.event.activity.ParticipantConfirmed;
import com.example.band_data.event.budget.BudgetUpdated;
import com.example.band_data.event.budget.PayMemberConfirmed;
import com.example.band_data.event.club.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Slf4j
//@Component
@RequiredArgsConstructor
@KafkaListener(topics = "data-topic", groupId = "data-consumer-group")
public class KafkaConsumerService {


    @KafkaHandler
    public void consumeEvent(ClubCreated event){
        System.out.println("success create club: " + event.getClubId());
    }
    @KafkaHandler
    public void consumeEvent(ClubClosed event){

    }
    @KafkaHandler
    public void consumeEvent(MemberCreated event){
        System.out.println("success create member: " + event.getMemberId());
    }
    @KafkaHandler
    public void consumeEvent(MemberLeft event){

    }
    @KafkaHandler
    public void consumeEvent(MemberBanned event){

    }
    @KafkaHandler
    public void consumeEvent(ActivityClosed event){

    }
    @KafkaHandler
    public void consumeEvent(ActivityCanceled event){

    }
    @KafkaHandler
    public void consumeEvent(ParticipantConfirmed event){
        System.out.println("yaho!");
    }
    @KafkaHandler
    public void consumeEvent(BudgetUpdated event){
        System.out.println("yuhoo~");
    }
    @KafkaHandler
    public void consumeEvent(PayMemberConfirmed event){

    }

    @KafkaHandler(isDefault = true)
    public void consume(Event event, Acknowledgment acknowledgment){
        throw new UnknownEventException("알 수 없는 이벤트");
    }
}
