package com.example.band_data.data.club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClubDataRepository extends JpaRepository<ClubData, Long> {

    public Optional<ClubData> findByClubIdAndDate(Long clubId, Integer date);

    @Query("SELECT cd FROM ClubData cd WHERE cd.clubId=:clubId AND cd.date < :date ORDER BY cd.date DESC")
    public Page<ClubData> findListByDate(Long clubId, Integer date, Pageable pageable);

}
