package com.demo.slk.application.airtel_wynk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.slk.application.airtel_wynk.pojo.ApiResponse;
import com.demo.slk.application.airtel_wynk.pojo.Artist;
import com.demo.slk.application.airtel_wynk.service.ArtistService;

@RestController
@RequestMapping(value = "v1/api")
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@PostMapping(value = "artist/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> add(@RequestBody Artist artist) throws Throwable{
		artistService.save(artist);
		ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				"Artist is registered");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
	}

}
