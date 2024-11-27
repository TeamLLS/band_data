package com.example.band_data;

import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.club.ClubDataRepository;
import com.example.band_data.data.member.MemberData;
import com.example.band_data.data.member.MemberDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class DummyUtil {

    private final ClubDataRepository clubDataRepository;
    private final MemberDataRepository memberDataRepository;

    @Transactional
    @PostConstruct
    public void makeDummy(){

        ClubData saved1 = new ClubData(1L, 1L, 202310, 5, 3, 1, 3, 1, 100000, 90000);
        ClubData saved2 = new ClubData(2L, 1L, 202311, 7, 1, 0, 4, 0, 150000, 170000);
        ClubData saved3 = new ClubData(3L, 1L, 202312, 2, 5, 1, 1, 3, 40000, 30000);
        ClubData saved4 = new ClubData(4L, 1L, 202401, 0, 9, 0, 2, 1, 70000, 50000);
        ClubData saved5 = new ClubData(5L, 1L, 202402, 3, 5, 2, 2, 0, 100000, 110000);
        ClubData saved6 = new ClubData(6L, 1L, 202403, 1, 2, 0, 3, 0, 120000, 150000);
        ClubData saved7 = new ClubData(7L, 1L, 202404, 2, 7, 0, 1, 1, 50000, 40000);
        ClubData saved8 = new ClubData(8L, 1L, 202405, 4, 1, 0, 4, 0, 190000, 220000);
        ClubData saved9 = new ClubData(9L, 1L, 202406, 6, 5, 1, 2, 1, 60000, 50000);

        clubDataRepository.save(saved1);
        clubDataRepository.save(saved2);
        clubDataRepository.save(saved3);
        clubDataRepository.save(saved4);
        clubDataRepository.save(saved5);
        clubDataRepository.save(saved6);
        clubDataRepository.save(saved7);
        clubDataRepository.save(saved8);
        clubDataRepository.save(saved9);


        MemberData saved10 = new MemberData(10L, saved1, 1L, 202310, 2, 0, 0, 3, 0, 0, 10000, 0, 0);
        MemberData saved11 = new MemberData(11L, saved2, 1L, 202311, 2, 1, 0, 2, 1, 1, 11000, 3000, 5000);
        MemberData saved12 = new MemberData(12L, saved3, 1L, 202312, 0, 0, 1, 1, 0, 0, 5000, 0, 0);
        MemberData saved13 = new MemberData(13L, saved4, 1L, 202401, 1, 0, 0, 1, 0, 0, 4000, 0, 0);
        MemberData saved14 = new MemberData(14L, saved5, 1L, 202402, 1, 0, 1, 0, 1, 0, 0, 2000, 0);
        MemberData saved15 = new MemberData(15L, saved6, 1L, 202403, 1, 1, 0, 1, 0, 1, 3000, 0, 4000);
        MemberData saved16 = new MemberData(16L, saved7, 1L, 202404, 0, 0, 0, 1, 1, 0, 5000, 6000, 0);
        MemberData saved17 = new MemberData(17L, saved8, 1L, 202405, 2, 0, 1, 1, 1, 1, 4000, 3000, 5000);
        MemberData saved18 = new MemberData(18L, saved9, 1L, 202406, 0, 1, 0, 0, 1, 0, 0, 7000, 0);


        memberDataRepository.save(saved10);
        memberDataRepository.save(saved11);
        memberDataRepository.save(saved12);
        memberDataRepository.save(saved13);
        memberDataRepository.save(saved14);
        memberDataRepository.save(saved15);
        memberDataRepository.save(saved16);
        memberDataRepository.save(saved17);
        memberDataRepository.save(saved18);

    }



}
