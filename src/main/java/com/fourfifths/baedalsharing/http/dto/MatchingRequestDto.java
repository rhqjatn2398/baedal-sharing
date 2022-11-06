package com.fourfifths.baedalsharing.http.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Builder
public class MatchingRequestDto {
    private final String registrationToken;
    private final Double latitude;
    private final Double longitude;
    private final LocalDateTime dateTime;
}