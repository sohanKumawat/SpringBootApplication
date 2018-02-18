package com.demo.slk.application.airtel_wynk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.slk.application.airtel_wynk.Dao.PlayListRepository;
import com.demo.slk.application.airtel_wynk.pojo.BasePojo;
import com.demo.slk.application.airtel_wynk.pojo.Item;

@Service
public class PlayListService implements BaseService {

	@Autowired
	PlayListRepository playRepo;

	@Override
	public <T extends BasePojo> String save(BasePojo pojo) throws Throwable{
		playRepo.save((Item)pojo);
		return "OK";
	}

	@Override
	public String delete(String id) throws Throwable{
		playRepo.delete(id);
		return "OK";
	}

	public boolean isAlreadyInPlayList(String userId, String artistId, String songId) {
		Optional<Item> play = playRepo.findByUserIdAndSongIdAndArtistId(userId, songId,artistId);
		return play.isPresent();
	}
	public Optional<List<Item>> songsByUserId(String userId) {
		Optional<List<Item>> items = playRepo.findByUserId(userId);
		return items;
	}

}
