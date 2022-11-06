package com.fourfifths.baedalsharing.domain.matching;

import com.fourfifths.baedalsharing.domain.exeption.PastTimeReservationException;
import com.fourfifths.baedalsharing.http.dto.MatchingRequestDto;
import com.fourfifths.baedalsharing.http.errorcode.CommonErrorCode;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MatchingService {
    Clustering clustering;
    Map<LocalDateTime, List<Reservation>> reservations;

    public MatchingService(Clustering clustering) {
        this.clustering = clustering;
        reservations = new HashMap<>();
    }

    public void enqueue(MatchingRequestDto matchingRequestDto) throws PastTimeReservationException {
        String token = matchingRequestDto.getRegistrationToken();
        LocalDateTime dateTime = matchingRequestDto.getDateTime();
        Double latitude = matchingRequestDto.getLatitude();
        Double longitude = matchingRequestDto.getLongitude();
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new PastTimeReservationException(CommonErrorCode.PAST_TIME_RESERVATION);
        }

        LocalDateTime truncatedDatetime = dateTime.truncatedTo(ChronoUnit.MINUTES);
        if (!reservations.containsKey(truncatedDatetime)) {
            reservations.put(truncatedDatetime, new LinkedList<>(Arrays.asList(new Reservation(token, dateTime, latitude, longitude))));
            return;
        }

        reservations.get(truncatedDatetime).add(new Reservation(token, dateTime, latitude, longitude));
    }

    @Scheduled(cron = "0 0 * * * ?")
    public Dataset[] match() {
        LocalDateTime truncatedDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Reservation> list = reservations.get(truncatedDateTime);
        Dataset dataset = new DefaultDataset();
        list.stream().forEach(reservation -> {
            double[] attributes = new double[] {reservation.getLatitude(), reservation.getLongitude()};
            dataset.add(new DenseInstance(attributes));
        });
        return clustering.cluster(list.size() / 4, dataset);
    }

    public void cancel() {

    }
}