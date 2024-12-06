package com.example.band_data.data.member;

import com.example.band_data.data.member.domain.MemberData;
import com.example.band_data.data.member.domain.MemberSubData;
import com.example.band_data.data.member.form.MemberDataDto;
import com.example.band_data.data.member.repository.MemberDataRepository;
import com.example.band_data.data.member.repository.MemberSubDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberDataStore {

    private final MemberDataRepository memberDataRepository;
    private final MemberSubDataRepository memberSubDataRepository;

    public MemberData save(MemberData memberData){
        return memberDataRepository.save(memberData);
    }
    public MemberData findByMemberId(Long memberId, Integer date){
        return memberDataRepository.findByMemberIdAndDate(memberId, date).orElseThrow();
    }

    public MemberSubData saveSub(MemberSubData memberSubData){
        return memberSubDataRepository.save(memberSubData);
    }

    public List<MemberData> findListByData(Long memberId, Integer fromDate, Integer toDate){
        return memberDataRepository.findListByDate(memberId, fromDate, toDate);
    }

    public List<MemberDataDto> findSumListByClubId(Long clubId, Integer fromDate, Integer toDate){
        return memberDataRepository.findSumListByClubId(clubId, fromDate, toDate);
    }
    public MemberDataDto findSumByMemberId(Long memberId, Integer fromDate, Integer toDate){
        return memberDataRepository.findSumByMemberId(memberId, fromDate, toDate).orElse(null);
    }

    public MemberSubData findSubByMemberId(Long memberId){
        return memberSubDataRepository.findByMemberId(memberId).orElseThrow();
    }

    public List<MemberSubData> findSubListByClubId(Long clubId){
        return memberSubDataRepository.findListByClubId(clubId);
    }

}
