package com.demo.slk.application.airtel_wynk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.slk.application.airtel_wynk.Dao.ArtistRepository;
import com.demo.slk.application.airtel_wynk.pojo.Artist;
import com.demo.slk.application.airtel_wynk.pojo.BasePojo;

@Service
public class ArtistService implements BaseService {

	@Autowired
	ArtistRepository artistReppo;
	@Override
	public <T extends BasePojo> String save(BasePojo pojo) throws Throwable{
		artistReppo.save((Artist)pojo);
		return "OK";
	}

	@Override
	public String delete(String id) throws Throwable{
		artistReppo.delete(id);
		return "OK";
	}
	public boolean findById(String artistId) {
		Optional<Artist> artist= artistReppo.findByArtistId(artistId);
		return artist.isPresent();
	}
}