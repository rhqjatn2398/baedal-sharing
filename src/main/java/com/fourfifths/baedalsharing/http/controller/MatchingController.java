package com.fourfifths.baedalsharing.http.controller;

import com.fourfifths.baedalsharing.domain.matching.MatchingService;
import com.fourfifths.baedalsharing.http.dto.MatchingRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchingController {
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/matching/current")
    public ResponseEntity<String> current(@RequestBody MatchingRequestDto matchingRequestDto) {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/matching/reservation")
    public ResponseEntity<String> makeReservation(@RequestBody MatchingRequestDto matchingRequestDto) {
        matchingService.enqueue(matchingRequestDto);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/matching/cancel")
    public ResponseEntity<String> cancelReservation(@RequestParam String registrationToken) {
        matchingService.cancel();
        return ResponseEntity.ok("success");
    }
}