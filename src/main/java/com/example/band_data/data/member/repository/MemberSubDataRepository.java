package com.example.band_data.data.member.repository;

import com.example.band_data.data.member.domain.MemberSubData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberSubDataRepository extends JpaRepository<MemberSubData, Long> {

    public Optional<MemberSubData> findByMemberId(Long memberId);

    @Query("SELECT msd FROM MemberSubData msd WHERE msd.clubId=:clubId AND msd.active=true")
    public List<MemberSubData> findListByClubId(Long clubId);
}
