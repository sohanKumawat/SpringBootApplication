package com.demo.slk.application.airtel_wynk.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.slk.application.airtel_wynk.pojo.ApiResponse;
import com.demo.slk.application.airtel_wynk.pojo.ArtistSongInput;
import com.demo.slk.application.airtel_wynk.pojo.Song;
import com.demo.slk.application.airtel_wynk.pojo.exception.NonRecoverableException;
import com.demo.slk.application.airtel_wynk.service.SongService;
import com.demo.slk.application.airtel_wynk.service.ValidationService;

@RestController
@RequestMapping(value = "v1/api/wynk")
public class SongController {

	@Autowired
	SongService songService;

	@PostMapping(value = "/publish", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ApiResponse> addSong(@RequestBody ArtistSongInput artistSong) throws Throwable {
		try {

			if (ValidationService.songPublishedValidation(artistSong)) {
				String songId = artistSong.getSong();
				List<String> aidList = artistSong.getArtists();
				for (String id : aidList) {
					Song song = new Song();
					song.setArtistId(id);
					song.setSongId(songId);
					songService.save(song);
				}
			}
		} catch (JSONException e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			throw new NonRecoverableException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}
		ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				"Song published against artist");
		apiResponse.setApiData("Song published against artist");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
	}

}
