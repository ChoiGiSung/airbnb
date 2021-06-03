package com.codesquad.coco.global.exception;

public class ErrorReason {

    private String message;

    public ErrorReason(String message) {
        this.message = message;
    }

    public static ErrorReason of(ErrorCode code) {
        return new ErrorReason(code.getMessage());
    }

    public static ErrorReason of(String message) {
        return new ErrorReason(message);
    }

    public String getMessage() {
        return message;
    }
}
