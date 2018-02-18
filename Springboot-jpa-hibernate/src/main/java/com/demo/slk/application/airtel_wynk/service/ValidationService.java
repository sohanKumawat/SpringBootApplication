package com.demo.slk.application.airtel_wynk.service;

import org.springframework.http.HttpStatus;

import com.demo.slk.application.airtel_wynk.pojo.ArtistSongInput;
import com.demo.slk.application.airtel_wynk.pojo.UserArtistInput;
import com.demo.slk.application.airtel_wynk.pojo.exception.NonRecoverableException;

public class ValidationService {

	public static boolean userFollowValidation(UserArtistInput input) throws Throwable {
		if(null==input) throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		try {
			if (!(input.getUser() != null && input.getArtists()!=null && input.getArtists().size() > 0))
				throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
			return true;
		} catch (Exception e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		}
	}

	public static boolean songPublishedValidation(ArtistSongInput input) throws Throwable {
		if(null==input) throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		try {
			if (!(input.getSong() != null && input.getArtists()!=null && input.getArtists().size() > 0))
				throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
			return true;
		} catch (Exception e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		}
	}
	
	/*
	public static boolean userFollowValidation(ArtistSongInput json) throws Throwable {
		try {
			JSONObject jsonObj = new JSONObject(json);
			String uId = jsonObj.getString("user");
			JSONArray artistJson = jsonObj.getJSONArray("artist");
			if (!(uId != null && artistJson.length() > 0))
				throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
			return true;
		} catch (JSONException e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		}
	}

	public static boolean songPublishedValidation(String json) throws Throwable {
		try {
			JSONObject jsonObj = new JSONObject(json);
			String song = jsonObj.getString("song");
			JSONArray artistJson = jsonObj.getJSONArray("artists");
			if (!(song != null && artistJson.length() > 0))
				throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
			return true;
		} catch (JSONException e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			throw new NonRecoverableException("invalid input parameters", HttpStatus.BAD_REQUEST.value());
		}
	}*/

}
