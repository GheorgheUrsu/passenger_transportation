package com.passengertransportation.demo.utils;

import lombok.Getter;

@Getter
public class ApiStatus {

    private final String status;
    private final String message;

    public ApiStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Api Error Status: [%s] Reason: %s", status, message);
    }
}
