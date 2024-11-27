package com.example.band_data.data.member;

import com.example.band_data.data.member.form.ParticipantDataItem;
import com.example.band_data.data.member.form.PayMemberDataItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data/member")
@RequiredArgsConstructor
public class MemberDataController {

    private final MemberDataService memberDataService;

    @GetMapping("/{memberId}/participant")
    public ResponseEntity<?> getParticipantTrend(@PathVariable Long memberId, @RequestParam(required = false) Integer period, @RequestParam int pageNo){
        List<ParticipantDataItem> list = memberDataService.getParticipantTrend(memberId, period, pageNo);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{memberId}/payMember")
    public ResponseEntity<?> getPayMemberTrend(@PathVariable Long memberId, @RequestParam(required = false) Integer period, @RequestParam int pageNo){
        List<PayMemberDataItem> list = memberDataService.getPayMemberTrend(memberId, period, pageNo);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        return ResponseEntity.ok().body(result);
    }
}
