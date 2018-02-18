package com.demo.slk.application.airtel_wynk.pojo.exception;

@SuppressWarnings("serial")
public class RecoverableException extends BaseException {

    public RecoverableException(Throwable cause, String message, int exceptionCode) {
        super(cause, message, exceptionCode);
    }

    public RecoverableException(String message, int exceptionCode) {
        super(message, exceptionCode);
    }
}
