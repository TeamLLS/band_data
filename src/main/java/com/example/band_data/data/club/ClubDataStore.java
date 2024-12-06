package com.example.band_data.data.club;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<ClubData> findListByDate(Long clubId, Integer fromDate, Integer toDate){
        return clubDataRepository.findListByDate(clubId, fromDate, toDate);
    }

    public Long findActCountByDate(Long clubId, Integer fromDate, Integer toDate){
        Long actTotal = clubDataRepository.findActSumByDate(clubId, fromDate, toDate);

        if(actTotal==null){
            return 0L;
        }

        return actTotal;
    }


}
