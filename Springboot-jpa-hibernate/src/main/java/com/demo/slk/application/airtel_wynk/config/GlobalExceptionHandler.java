package com.demo.slk.application.airtel_wynk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.slk.application.airtel_wynk.pojo.ApiResponse;
import com.demo.slk.application.airtel_wynk.pojo.exception.NonRecoverableException;
import com.demo.slk.application.airtel_wynk.pojo.exception.RecoverableException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@Configuration
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { Throwable.class })
	public ResponseEntity<ApiResponse> UnknowExceptionHandler(Throwable ex) {
		log.error(ex.getMessage());
		ApiResponse apiResponse = null;
		if (ex instanceof RecoverableException) {
			RecoverableException rex = (RecoverableException) ex;
			apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), rex.getMessage());
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (ex instanceof NonRecoverableException) {
			log.error(ex.getMessage());
			NonRecoverableException rex = (NonRecoverableException) ex;
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
					rex.getMessage());
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		} else {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.getReasonPhrase().toString(), ex.getMessage());
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/*
	 * @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class,
	 * MethodArgumentNotValidException.class, NoHandlerFoundException.class, })
	 * public ResponseEntity<Object> handleNoHandlerFoundException(Exception ex)
	 * { log.error(ex.getMessage()); ApiResponse apiResponse = new
	 * ApiResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
	 * HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase().toString(),
	 * ex.getMessage()); return new ResponseEntity<Object>(apiResponse,
	 * HttpStatus.NOT_FOUND); }
	 */

}
