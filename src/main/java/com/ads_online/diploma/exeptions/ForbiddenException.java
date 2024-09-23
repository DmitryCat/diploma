package com.ads_online.diploma.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String massager) {
        super(massager);
    }
}
