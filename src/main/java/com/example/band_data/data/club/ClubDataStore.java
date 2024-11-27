package com.example.band_data.data.club;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubDataStore {

    private final ClubDataRepository clubDataRepository;

    public ClubData save(ClubData clubData){
        return clubDataRepository.save(clubData);
    }

    public ClubData findByDate(Long clubId, Integer date){
        return clubDataRepository.findByClubIdAndDate(clubId, date).orElseThrow();
    }

    public Page<ClubData> findList(Long clubId, Integer date, Integer period, int pageNo, int pageSize){
        return clubDataRepository.findListByDate(clubId, date, PageRequest.of(pageNo, period*pageSize));
    }


}
