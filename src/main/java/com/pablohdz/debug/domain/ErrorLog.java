package com.pablohdz.debug.domain;

public class ErrorLog {
    private final String service;
    private final String message;

    public ErrorLog(String service, String message) {
        this.service = service;
        this.message = message;
    }

    @Override
    public String toString() {
        return "service = " + this.service +
            ", message = " + this.message;
    }
}
