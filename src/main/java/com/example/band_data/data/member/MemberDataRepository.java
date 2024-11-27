package com.example.band_data.data.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberDataRepository extends JpaRepository<MemberData, Long> {

    public Optional<MemberData> findByMemberIdAndDate(Long memberId, Integer date);

    @Query("SELECT md FROM MemberData md WHERE md.memberId=:memberId AND md.date < :date ORDER BY md.date DESC")
    public Page<MemberData> findListByDate(Long memberId, Integer date, Pageable pageable);

    @Query("SELECT md FROM MemberData md JOIN FETCH md.clubData WHERE md.memberId=:memberId AND md.date < :date ORDER BY md.date DESC")
    public Page<MemberData> findListWithClubDataByDate(Long memberId, Integer date, Pageable pageable);
}
