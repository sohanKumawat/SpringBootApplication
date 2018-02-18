package com.demo.slk.application.airtel_wynk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.slk.application.airtel_wynk.Dao.SongRepository;
import com.demo.slk.application.airtel_wynk.pojo.BasePojo;
import com.demo.slk.application.airtel_wynk.pojo.Song;

@Service
public class SongService implements BaseService {

	@Autowired
	SongRepository songRepo;
	@Override
	public <T extends BasePojo> String save(BasePojo pojo) throws Throwable{
		pojo=songRepo.save((Song)pojo);
		return ((Song)pojo).getSongId();
	}

	@Override
	public String delete(String id) throws Throwable{
		songRepo.delete(id);
		return "Song with id : "+id +" deleted";
	}
	public List<Song> findByArtistId(String artistid) {
		return songRepo.findByArtistId(artistid);
	}
}
