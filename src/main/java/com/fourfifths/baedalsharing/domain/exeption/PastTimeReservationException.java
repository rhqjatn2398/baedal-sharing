package com.fourfifths.baedalsharing.domain.exeption;

import com.fourfifths.baedalsharing.http.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PastTimeReservationException extends RuntimeException {
    private final ErrorCode errorCode;
}