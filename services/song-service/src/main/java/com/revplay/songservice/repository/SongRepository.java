package com.revplay.songservice.repository;

import com.revplay.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    // search by title
    List<Song> findByTitleContainingIgnoreCase(String keyword);

    // trending songs (top played)
    List<Song> findTop10ByOrderByPlayCountDesc();

}