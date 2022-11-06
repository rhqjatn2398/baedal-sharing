package com.fourfifths.baedalsharing.http.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {
    PAST_TIME_RESERVATION(HttpStatus.BAD_REQUEST, "Can not make reservation for past time");

    private final HttpStatus httpStatus;
    private final String message;
}