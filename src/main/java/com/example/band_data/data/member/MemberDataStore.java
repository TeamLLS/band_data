package com.example.band_data.data.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDataStore {

    private final MemberDataRepository memberDataRepository;

    public MemberData save(MemberData memberData){
        return memberDataRepository.save(memberData);
    }

    public MemberData findByMemberId(Long memberId, Integer date){
        return memberDataRepository.findByMemberIdAndDate(memberId, date).orElseThrow();
    }

    public Page<MemberData> findList(Long memberId, Integer date, Integer period, int pageNo, int pageSize){
        return memberDataRepository.findListByDate(memberId, date, PageRequest.of(pageNo, period*pageSize));
    }

    public Page<MemberData> findListWithClubData(Long memberId, Integer date, Integer period, int pageNo, int pageSize){
        return memberDataRepository.findListWithClubDataByDate(memberId, date, PageRequest.of(pageNo, period*pageSize));
    }
}
