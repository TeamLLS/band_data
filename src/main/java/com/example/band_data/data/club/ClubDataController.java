package com.example.band_data.data.club;


import com.example.band_data.data.club.form.ActivityDataItem;
import com.example.band_data.data.club.form.BudgetDataItem;
import com.example.band_data.data.club.form.MemberDataItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data/club")
@RequiredArgsConstructor
public class ClubDataController {

    private final ClubDataService clubDataService;

    @GetMapping("/{clubId}/member")
    public ResponseEntity<?> getMemberTrend(@PathVariable Long clubId, @RequestParam(required = false) Instant fromTime){

        List<MemberDataItem> list = clubDataService.getMemberTrend(clubId, fromTime);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{clubId}/activity")
    public ResponseEntity<?> getActivityTrend(@PathVariable Long clubId, @RequestParam(required = false) Instant fromTime){

        List<ActivityDataItem> list = clubDataService.getActivityTrend(clubId, fromTime);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{clubId}/budget")
    public ResponseEntity<?> getBudgetTrend(@PathVariable Long clubId, @RequestParam(required = false) Instant fromTime){

        List<BudgetDataItem> list = clubDataService.getBudgetTrend(clubId, fromTime);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        return ResponseEntity.ok().body(result);
    }

}
