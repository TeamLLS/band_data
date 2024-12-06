package com.example.band_data;

import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.club.ClubDataRepository;
import com.example.band_data.data.member.domain.MemberData;
import com.example.band_data.data.member.domain.MemberSubData;
import com.example.band_data.data.member.repository.MemberDataRepository;
import com.example.band_data.data.member.repository.MemberSubDataRepository;
import com.example.band_data.event.club.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Component
@RequiredArgsConstructor
public class DummyUtil {

    private final ClubDataRepository clubDataRepository;
    private final MemberDataRepository memberDataRepository;
    private final MemberSubDataRepository memberSubDataRepository;

    @Transactional
    @PostConstruct
    public void makeDummy(){

        ClubData saved1 = new ClubData(1L, 1L, 202311, 5, 3, 1, 3, 1, 100000, -90000);
        ClubData saved2 = new ClubData(2L, 1L, 202312, 7, 1, 0, 4, 0, 150000, -170000);
        ClubData saved3 = new ClubData(3L, 1L, 202403, 2, 5, 1, 1, 3, 40000, -30000);
        ClubData saved4 = new ClubData(4L, 1L, 202405, 0, 9, 0, 2, 1, 70000, -50000);
        ClubData saved5 = new ClubData(5L, 1L, 202406, 3, 5, 2, 2, 0, 100000, -110000);
        ClubData saved6 = new ClubData(6L, 1L, 202407, 1, 2, 0, 3, 0, 120000, -150000);
        ClubData saved7 = new ClubData(7L, 1L, 202409, 2, 7, 0, 1, 1, 50000, -40000);
        ClubData saved8 = new ClubData(8L, 1L, 202410, 4, 1, 0, 4, 0, 190000, -220000);
        ClubData saved9 = new ClubData(9L, 1L, 202411, 6, 5, 1, 2, 1, 60000, -50000);

        clubDataRepository.save(saved1);
        clubDataRepository.save(saved2);
        clubDataRepository.save(saved3);
        clubDataRepository.save(saved4);
        clubDataRepository.save(saved5);
        clubDataRepository.save(saved6);
        clubDataRepository.save(saved7);
        clubDataRepository.save(saved8);
        clubDataRepository.save(saved9);


        MemberData saved10 = new MemberData(10L, saved1.getClubId(), 1L, 202311, 5, 2, 0, 0, 3, 0, 0, 10000, 0, 0);
        MemberData saved11 = new MemberData(11L, saved2.getClubId(), 1L, 202312, 3, 2, 1, 0, 2, 1, 1, 11000, 3000, 5000);
        MemberData saved12 = new MemberData(12L, saved3.getClubId(), 1L, 202403, 0, 0, 0, 1, 1, 0, 0, 5000, 0, 0);
        MemberData saved13 = new MemberData(13L, saved4.getClubId(), 1L, 202405, 2, 1, 0, 0, 1, 0, 0, 4000, 0, 0);
        MemberData saved14 = new MemberData(14L, saved5.getClubId(), 1L, 202406, -1, 1, 0, 1, 0, 1, 0, 0, 2000, 0);
        MemberData saved15 = new MemberData(15L, saved6.getClubId(), 1L, 202407, 2, 1, 1, 0, 1, 0, 1, 3000, 0, 4000);
        MemberData saved16 = new MemberData(16L, saved7.getClubId(), 1L, 202409, 0, 0, 0, 0, 1, 1, 0, 5000, 6000, 0);
        MemberData saved17 = new MemberData(17L, saved8.getClubId(), 1L, 202410, 1, 2, 0, 1, 1, 1, 1, 4000, 3000, 5000);
        MemberData saved18 = new MemberData(18L, saved9.getClubId(), 1L, 202411, -1, 0, 1, 0, 0, 1, 0, 0, 7000, 0);

        MemberData saved19 = new MemberData(19L, saved1.getClubId(), 2L, 202311, 6, 3, 0, 0, 3, 0, 0, 15000, 0, 0);
        MemberData saved20 = new MemberData(20L, saved2.getClubId(), 2L, 202312, 6, 3, 0, 0, 3, 0, 0, 18000, 0, 5000);
        MemberData saved21 = new MemberData(21L, saved3.getClubId(), 2L, 202403, 0, 0, 0, 1, 1, 0, 0, 5000, 0, 0);
        MemberData saved22 = new MemberData(22L, saved4.getClubId(), 2L, 202405, 2, 1, 0, 0, 1, 0, 0, 4000, 0, 0);
        MemberData saved23 = new MemberData(23L, saved5.getClubId(), 2L, 202406, 3, 1, 1, 0, 2, 0, 0, 10000, 0, 0);
        MemberData saved24 = new MemberData(24L, saved6.getClubId(), 2L, 202407, 4, 2, 0, 0, 2, 0, 1, 9000, 0, 4000);
        MemberData saved25 = new MemberData(25L, saved7.getClubId(), 2L, 202409, -2, 0, 0, 1, 0, 1, 0, 0, 6000, 0);
        MemberData saved26 = new MemberData(26L, saved8.getClubId(), 2L, 202410, 4, 2, 1, 0, 3, 1, 0, 15000, 4000, 0);
        MemberData saved27 = new MemberData(27L, saved9.getClubId(), 2L, 202411, 1, 0, 1, 0, 1, 0, 0, 7000, 0, 0);

        MemberData saved28 = new MemberData(28L, saved5.getClubId(), 3L, 202406, 4, 2, 0, 0, 2, 0, 1, 8000, 0, 4000);
        MemberData saved29 = new MemberData(29L, saved6.getClubId(), 3L, 202407, 4, 2, 0, 0, 2, 0, 0, 10000, 0, 0);
        MemberData saved30 = new MemberData(30L, saved7.getClubId(), 3L, 202409, 2, 1, 0, 0, 1, 0, 0, 6000, 0, 0);
        MemberData saved31 = new MemberData(31L, saved8.getClubId(), 3L, 202410, 5, 2, 0, 0, 3, 0, 0, 11000, 0, 0);
        MemberData saved32 = new MemberData(32L, saved9.getClubId(), 3L, 202411, 0, 1, 0, 0, 0, 1, 0, 0, 7000, 0);

        MemberData saved33 = new MemberData(33L, saved5.getClubId(), 4L, 202406, 1, 1, 1, 0, 1, 1, 0, 5000, 5000, 0);
        MemberData saved34 = new MemberData(34L, saved6.getClubId(), 4L, 202407, 5, 2, 0, 0, 3, 0, 0, 14000, 0, 0);
        MemberData saved35 = new MemberData(35L, saved7.getClubId(), 4L, 202409, 1, 1, 0, 0, 0, 0, 1, 0, 0, 6000);
        MemberData saved36 = new MemberData(36L, saved8.getClubId(), 4L, 202410, 2, 2, 0, 1, 2, 1, 0, 11000, 4000, 0);
        MemberData saved37 = new MemberData(37L, saved9.getClubId(), 4L, 202411, 2, 1, 1, 0, 1, 0, 1, 4000, 0, 7000);

        MemberData saved38 = new MemberData(49L, saved6.getClubId(), 5L, 202407, 2, 2, 0, 0, 1, 1, 1, 5000, 4000, 4000);
        MemberData saved39 = new MemberData(50L, saved7.getClubId(), 5L, 202410, -1, 0, 1, 0, 0, 1, 0, 0, 6000, 0);
        MemberData saved40 = new MemberData(52L, saved9.getClubId(), 5L, 202411, 2, 1, 0, 0, 1, 0, 0, 7000, 0, 0);

        memberDataRepository.save(saved10);
        memberDataRepository.save(saved11);
        memberDataRepository.save(saved12);
        memberDataRepository.save(saved13);
        memberDataRepository.save(saved14);
        memberDataRepository.save(saved15);
        memberDataRepository.save(saved16);
        memberDataRepository.save(saved17);
        memberDataRepository.save(saved18);


        memberDataRepository.save(saved19);
        memberDataRepository.save(saved20);
        memberDataRepository.save(saved21);
        memberDataRepository.save(saved22);
        memberDataRepository.save(saved23);
        memberDataRepository.save(saved24);
        memberDataRepository.save(saved25);
        memberDataRepository.save(saved26);
        memberDataRepository.save(saved27);

        memberDataRepository.save(saved28);
        memberDataRepository.save(saved29);
        memberDataRepository.save(saved30);
        memberDataRepository.save(saved31);
        memberDataRepository.save(saved32);

        memberDataRepository.save(saved33);
        memberDataRepository.save(saved34);
        memberDataRepository.save(saved35);
        memberDataRepository.save(saved36);
        memberDataRepository.save(saved37);

        memberDataRepository.save(saved38);
        memberDataRepository.save(saved39);
        memberDataRepository.save(saved40);


        MemberSubData subSaved1 = new MemberSubData(1L, 1L, "Dummy_userA", "허연준", Role.MANAGER, Instant.now().minusSeconds(110000), Instant.now().minusSeconds(150000), 21000);
        MemberSubData subSaved2 = new MemberSubData(1L, 2L, "Dummy_userB", "임윤빈", Role.OWNER, Instant.now().minusSeconds(110000), Instant.now().minusSeconds(150000), 10000);
        MemberSubData subSaved3 = new MemberSubData(1L, 3L, "Dummy_userC", "권미르", Role.REGULAR, Instant.now().minusSeconds(110000), Instant.now().minusSeconds(150000), 7000);
        MemberSubData subSaved4 = new MemberSubData(1L, 4L, "Dummy_userD", "최은", Role.REGULAR, Instant.now().minusSeconds(110000), Instant.now().minusSeconds(150000), 9000);
        MemberSubData subSaved5 = new MemberSubData(1L, 5L, "Dummy_userF", "하도준", Role.REGULAR, Instant.now().minusSeconds(110000), Instant.now().minusSeconds(150000), 10000);

        memberSubDataRepository.save(subSaved1);
        memberSubDataRepository.save(subSaved2);
        memberSubDataRepository.save(subSaved3);
        memberSubDataRepository.save(subSaved4);
        memberSubDataRepository.save(subSaved5);

    }



}
