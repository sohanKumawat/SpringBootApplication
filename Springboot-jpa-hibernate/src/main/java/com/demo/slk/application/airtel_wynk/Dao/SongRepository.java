package com.demo.slk.application.airtel_wynk.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.slk.application.airtel_wynk.pojo.Song;

@Transactional
@Repository
public interface SongRepository extends JpaRepository<Song, String> {
	List<Song> findByArtistId(String artistid);
}
