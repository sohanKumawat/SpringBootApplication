package com.demo.slk.application.airtel_wynk.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.slk.application.airtel_wynk.pojo.ApiResponse;
import com.demo.slk.application.airtel_wynk.pojo.Item;
import com.demo.slk.application.airtel_wynk.pojo.User;
import com.demo.slk.application.airtel_wynk.pojo.UserArtistInput;
import com.demo.slk.application.airtel_wynk.pojo.exception.NonRecoverableException;
import com.demo.slk.application.airtel_wynk.service.UserService;
import com.demo.slk.application.airtel_wynk.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "v1/api/wynk")
public class UserController {

	@Autowired
	UserService userservice;
	ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "user/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> add(@RequestBody User user) throws Throwable {

		userservice.save(user);
		ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				"User is registered");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
	}

	@PostMapping(value = "/follow", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> follow(@RequestBody UserArtistInput userArtist) throws Throwable {
		List<String> aidList=null;
		try {
			if (ValidationService.userFollowValidation(userArtist)) {
				String uId = userArtist.getUser();
				aidList = userArtist.getArtists();
				userservice.follow(uId, aidList);
			}
		} catch (JSONException e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

		ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				"User started the following artist "+StringUtils.collectionToDelimitedString(aidList, ","));
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/unfollow", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> unFollow(@RequestBody UserArtistInput userArtist) throws Throwable {
		List<String> aidList=null;
		try {
			if (ValidationService.userFollowValidation(userArtist)) {
				String uId = userArtist.getUser();
				aidList = userArtist.getArtists();
				userservice.unFollow(uId, aidList);
			}
		} catch (JSONException e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

		ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				"User unfollow the artist "+StringUtils.collectionToDelimitedString(aidList, ","));
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/playlist", produces = "application/json")
	public ResponseEntity<ApiResponse> songByUserId(@RequestParam(value = "user", required = true) String user)
			throws Throwable {
		if (null == user)
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		List<Item> itemList = userservice.songsByUserId(user);
		ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				"User play list");
		apiResponse.setApiData(itemList);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
