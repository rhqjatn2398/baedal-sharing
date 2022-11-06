package com.fourfifths.baedalsharing.domain.matching;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    private String token;
    private LocalDateTime dateTime;
    private Double latitude;
    private Double longitude;
}