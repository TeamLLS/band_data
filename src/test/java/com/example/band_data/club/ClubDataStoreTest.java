package com.example.band_data.club;


import com.example.band_data.data.club.ClubData;
import com.example.band_data.data.club.ClubDataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Import(ClubDataStoreTest.class)
public class ClubDataStoreTest {

    @Autowired
    ClubDataStore clubDataStore;

    List<ClubData> clubDatas;

    @BeforeEach
    public void saveClubDatas(){

        ClubData saved1 = new ClubData(1L, 1L, 202410, 5, 3, 1, 3, 1, 100000, 90000);
        ClubData saved2 = new ClubData(2L, 1L, 202411, 7, 1, 0, 4, 0, 150000, 120000);
        ClubData saved3 = new ClubData(3L, 1L, 202412, 2, 5, 1, 1, 3, 40000, 30000);
        ClubData saved4 = new ClubData(4L, 1L, 202501, 0, 2, 0, 2, 1, 70000, 50000);
        ClubData saved5 = new ClubData(5L, 1L, 202502, 3, 1, 2, 2, 0, 100000, 110000);
        ClubData saved6 = new ClubData(6L, 1L, 202503, 1, 2, 0, 3, 0, 120000, 9000);
        ClubData saved7 = new ClubData(7L, 1L, 202504, 2, 3, 0, 1, 1, 50000, 40000);
        ClubData saved8 = new ClubData(8L, 1L, 202505, 4, 1, 0, 4, 0, 190000, 200000);
        ClubData saved9 = new ClubData(9L, 1L, 202506, 6, 1, 1, 2, 1, 60000, 50000);

        clubDataStore.save(saved1);
        clubDataStore.save(saved2);
        clubDataStore.save(saved3);
        clubDataStore.save(saved4);
        clubDataStore.save(saved5);
        clubDataStore.save(saved6);
        clubDataStore.save(saved7);
        clubDataStore.save(saved8);
        clubDataStore.save(saved9);

        clubDatas = new ArrayList<>();
        clubDatas.add(saved1);
        clubDatas.add(saved2);
        clubDatas.add(saved3);
        clubDatas.add(saved4);
        clubDatas.add(saved5);
        clubDatas.add(saved6);
        clubDatas.add(saved7);
        clubDatas.add(saved8);
        clubDatas.add(saved9);

    }

    @Test
    public void getListTest(){
        clubDataStore.findPeriodList(1L, 1, 0, 5);
    }
}
