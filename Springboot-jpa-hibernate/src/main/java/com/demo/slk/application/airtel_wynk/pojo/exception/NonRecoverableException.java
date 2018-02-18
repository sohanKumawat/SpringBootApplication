package com.demo.slk.application.airtel_wynk.pojo.exception;

/**
 * Created by Sohan Kumawat
 */
@SuppressWarnings("serial")
public class NonRecoverableException extends BaseException {

    public NonRecoverableException(Throwable cause, String message, int exceptionCode) {
        super(cause, message, exceptionCode);
    }

    public NonRecoverableException(String message, int exceptionCode) {
        super(message, exceptionCode);
    }
}
