package com.demo.slk.application.airtel_wynk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.slk.application.airtel_wynk.Dao.FollowerDaoManager;
import com.demo.slk.application.airtel_wynk.Dao.UserRepository;
import com.demo.slk.application.airtel_wynk.pojo.Artist;
import com.demo.slk.application.airtel_wynk.pojo.BasePojo;
import com.demo.slk.application.airtel_wynk.pojo.Item;
import com.demo.slk.application.airtel_wynk.pojo.Song;
import com.demo.slk.application.airtel_wynk.pojo.User;

@Service
public class UserService implements BaseService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	ArtistService artistService;
	@Autowired
	SongService songService;
	@Autowired
	PlayListService playListService;

	@Autowired
	FollowerDaoManager followerManager;

	@Override
	public <T extends BasePojo> String save(BasePojo pojo) throws Throwable{
		userRepo.save((User)pojo);
		return "Ok";
	}

	@Override
	public String delete(String id) throws Throwable{
		userRepo.delete(id);
		return "user id deleted with id - "+id;
	}

	public String follow(String userId, List<String> artistId) throws Throwable {
		String response="userId started following following artist ";
		for (String id : artistId) {
			response=response+" "+id;
			List<Song> songList;
			Item item = null;
			List<Item> items = null;
			
			if (artistService.findById(id)) {
				songList = songService.findByArtistId(id);
				if (null != songList && songList.size() > 0)
					items = new ArrayList<>();
				for (Song song : songList) {
					item = new Item();
					item.setArtistId(id);
					item.setPTitle("artist name " + id);
					item.setSongId(song.getSongId());
					item.setUserId(userId);
					items.add(item);
				}
			} else {
				Artist artist = new Artist();
				artist.setArtistId(id);
				artist.setArtistName(id);
				artistService.save(artist);
			}
			if (!userById(userId)) {
				User user = new User();
				user.setUserId(userId);
				user.setUserName(userId);
				userRepo.save(user);
			}
			followUserArtist(userId, id);
			for (Item itm : items) {
				addSongsToUser(itm);
			}
		}
		return response;
	}

	public void addSongsToUser(Item item) throws Throwable{
		boolean status = playListService.isAlreadyInPlayList(item.getUserId(), item.getArtistId(), item.getSongId());
		if (!status) {
			playListService.save(item);
		}
	}

	public void followUserArtist(String userId, String artistId) throws Throwable {
		followerManager.followUserArtist(userId, artistId);
	}

	public void unFollow(String userId, List<String> artistId) throws Throwable {
		for (String id : artistId) {
			followerManager.unFollowUserArtist(userId, id);
		}
	}

	public Boolean userById(String userId) {
		Optional<User> user = userRepo.findByUserId(userId);
		return user.isPresent();
	}
	
	public List<Item> songsByUserId(String userId) {
		Optional<List<Item>> items = playListService.songsByUserId(userId);
		return items.isPresent()?items.get(): (List<Item>) new ArrayList<Item>();
	}
}
