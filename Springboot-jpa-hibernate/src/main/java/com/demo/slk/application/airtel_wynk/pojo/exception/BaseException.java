package com.demo.slk.application.airtel_wynk.pojo.exception;

@SuppressWarnings("serial")
public class BaseException extends Exception {
    private String message;
    private int exceptionCode;

    public BaseException(Throwable cause, String message,int exceptionCode) {
        super(cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    public BaseException(String message,int exceptionCode) {
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }

}

