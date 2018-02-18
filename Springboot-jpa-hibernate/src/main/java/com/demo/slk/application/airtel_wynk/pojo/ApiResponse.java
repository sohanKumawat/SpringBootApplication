package com.demo.slk.application.airtel_wynk.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {
	@NonNull
	private Integer statusCode;// 201,401,2001
	@NonNull
	private String apiStatus;// success,error
	@NonNull
	private String message;
	private String description;//
	private Object apiData;
}

