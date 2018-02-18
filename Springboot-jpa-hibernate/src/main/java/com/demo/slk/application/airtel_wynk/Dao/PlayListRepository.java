package com.demo.slk.application.airtel_wynk.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.slk.application.airtel_wynk.pojo.Item;

@Transactional
@Repository
public interface PlayListRepository extends JpaRepository<Item, String> {

	Optional<Item> findByUserIdAndSongIdAndArtistId(String userId, String songId,String artistid);

	Optional<List<Item>> findByUserId(String userId);

	//Optional<List<Item>> findByArtistId(String artistId);
}
