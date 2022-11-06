package com.fourfifths.baedalsharing.domain.matching;

import com.fourfifths.baedalsharing.domain.exeption.PastTimeReservationException;
import com.fourfifths.baedalsharing.http.dto.MatchingRequestDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.javaml.core.Dataset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@Slf4j
class MatchingServiceTest {
    MatchingService matchingService;

    @Autowired
    public MatchingServiceTest(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @Test
    void enque() throws PastTimeReservationException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String token = UUID.randomUUID().toString();
            LocalDateTime dateTime = LocalDateTime.now().plusHours(random.nextInt(96) - 48);
            Double latitude = random.nextDouble() * 180 - 90;
            Double longitude = random.nextDouble() * 360 - 180;

            MatchingRequestDto matchingRequestDto = MatchingRequestDto.builder().latitude(latitude)
                    .registrationToken(token)
                    .longitude(longitude)
                    .dateTime(dateTime)
                    .build();
            matchingService.enqueue(matchingRequestDto);
        }

        matchingService.reservations.forEach((dateTime, reservations) -> {
            log.info(String.valueOf(dateTime));
            log.info(reservations.toString());
        });
    }

    @Test
    void match() throws PastTimeReservationException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            String token = UUID.randomUUID().toString();
            LocalDateTime dateTime = LocalDateTime.now().plusHours(random.nextInt(96) - 48);
            Double latitude = random.nextDouble() * 10 - 5 + 38; // 대한민국 위도범위 33 ~ 43
            Double longitude = random.nextDouble() * 8 - 4 + 128; // 대한민국 경도범위 124 ~ 132

            MatchingRequestDto matchingRequestDto = MatchingRequestDto.builder().latitude(latitude)
                    .registrationToken(token)
                    .longitude(longitude)
                    .dateTime(dateTime)
                    .build();
            matchingService.enqueue(matchingRequestDto);
        }
        for (Dataset dataset : matchingService.match()) {
            log.info(dataset.toString());
        }
    }
}