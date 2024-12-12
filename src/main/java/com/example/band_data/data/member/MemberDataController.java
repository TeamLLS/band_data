package com.example.band_data.data.member;

import com.example.band_data.data.member.form.MemberScoreItem;
import com.example.band_data.data.member.form.ParticipantDataItem;
import com.example.band_data.data.member.form.PayMemberDataItem;
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
@RequestMapping("/data/member")
@RequiredArgsConstructor
public class MemberDataController {

    private final MemberDataService memberDataService;

    @GetMapping("/{clubId}/{memberId}/participant")
    public ResponseEntity<?> getParticipantTrend(@PathVariable Long clubId, @PathVariable Long memberId, @RequestParam(required = false) Instant fromTime){
        List<ParticipantDataItem> list = memberDataService.getParticipantTrend(clubId, memberId, fromTime);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{clubId}/{memberId}/payMember")
    public ResponseEntity<?> getPayMemberTrend(@PathVariable Long clubId, @PathVariable Long memberId, @RequestParam(required = false) Instant fromTime){
        List<PayMemberDataItem> list = memberDataService.getPayMemberTrend(clubId, memberId, fromTime);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{clubId}/{memberId}/score")
    public ResponseEntity<?> getMemberScore(@PathVariable Long clubId, @PathVariable Long memberId){
        return ResponseEntity.ok().body(memberDataService.getMemberScore(clubId, memberId));
    }

    @GetMapping("/{clubId}/rank")
    public ResponseEntity<?> getMemberRank(@PathVariable Long clubId, @RequestParam(required = false) Integer option){
        List<MemberScoreItem> list = memberDataService.getMemberScores(clubId, option);
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }
}
