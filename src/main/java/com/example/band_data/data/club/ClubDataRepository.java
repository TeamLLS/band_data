package com.example.band_data.data.club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubDataRepository extends JpaRepository<ClubData, Long> {

    public Optional<ClubData> findByClubIdAndDate(Long clubId, Integer date);

    @Query("SELECT cd FROM ClubData cd WHERE cd.clubId=:clubId " +
            "AND cd.date >= :fromDate AND cd.date < :toDate ORDER BY cd.date DESC")
    public List<ClubData> findListByDate(Long clubId, Integer fromDate, Integer toDate);


    @Query("SELECT SUM(cd.actCloseCount) FROM ClubData cd WHERE cd.clubId=:clubId " +
            "AND cd.date >= :fromDate AND cd.date < :toDate" )
    public Long findActSumByDate(Long clubId, Integer fromDate, Integer toDate);
}
