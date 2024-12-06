package com.example.band_data.data.member.repository;

import com.example.band_data.data.member.domain.MemberData;
import com.example.band_data.data.member.form.MemberDataDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberDataRepository extends JpaRepository<MemberData, Long> {

    public Optional<MemberData> findByMemberIdAndDate(Long memberId, Integer date);

    @Query("SELECT md FROM MemberData md WHERE md.memberId=:memberId " +
            "AND md.date >= :fromDate AND md.date < :toDate ORDER BY md.date DESC")
    public List<MemberData> findListByDate(Long memberId, Integer fromDate, Integer toDate);



    @Query("SELECT new com.example.band_data.data.member.form.MemberDataDto(md.clubId, md.memberId, SUM(md.point), " +
            "SUM(md.actAttendCount), SUM(md.actLateAttendCount), SUM(md.actLateNotAttendCount), " +
            "SUM(md.payCount), SUM(md.unPayCount), SUM(md.latePayCount), SUM(md.payAmount), SUM(md.unPayAmount), SUM(md.latePayAmount)) " +
            "FROM MemberData md " +
            "WHERE md.clubId = :clubId " +
            "AND md.date >= :fromDate AND md.date < :toDate " +
            "GROUP BY md.memberId")
    public List<MemberDataDto> findSumListByClubId(Long clubId, Integer fromDate, Integer toDate);

    @Query("SELECT new com.example.band_data.data.member.form.MemberDataDto(md.clubId, md.memberId, SUM(md.point), " +
            "SUM(md.actAttendCount), SUM(md.actLateAttendCount), SUM(md.actLateNotAttendCount), " +
            "SUM(md.payCount), SUM(md.unPayCount), SUM(md.latePayCount), SUM(md.payAmount), SUM(md.unPayAmount), SUM(md.latePayAmount)) " +
            "FROM MemberData md  " +
            "WHERE md.memberId = :memberId " +
            "AND md.date >= :fromDate AND md.date < :toDate ")
    public Optional<MemberDataDto> findSumByMemberId(Long memberId, Integer fromDate, Integer toDate);
}
