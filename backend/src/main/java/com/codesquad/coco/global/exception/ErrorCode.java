package com.codesquad.coco.global.exception;

public enum ErrorCode {

    /**
     * 비즈니스 예외
     **/
    RESERVED_FOR_THAT_DATE(422, "그 날에는 예약이 이미 있음"),
    OVER_CAPACITY(422, "수용 인원 초과"),
    TOTAL_PRICE_NON_MATCH(422, "클라이언트의 총액과 일치하지 않음 : "),

    /**
     * notFound 예외
     **/
    ENTITY_NOT_FOUND(404, "찾는 엔티티는 없습니다"),
    ROOM_NOT_FOUND(404, "찾는 방이 없습니다"),
    USER_NOT_FOUND(404, "해당하는 유저 없음"),

    /**
     * 잘못된 input
     **/
    INVALID_INPUT(400, "잘못된 입력"),

    /**
     * 인증 관련 예외
     **/
    NOT_LOGGED_ID(401, "로그인 상태가 아닙니다"),
    UNKNOWN_DEVICE(404, "올바른 device 접근이 아닙니다"),
    UNAUTHORIZED_JWT(400, "jwt를 확인 하세요"),
    MAYBE_REST_TEMPLATE(400, "잘못된 요청"),


    /**
     * 기타 에러
     **/
    INTERNAL_SERVER_ERROR(500, "서버에러");


    private int httpStatus;
    private String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode plusMessage(String valueOf) {
        this.message += valueOf;
        return this;
    }
}
