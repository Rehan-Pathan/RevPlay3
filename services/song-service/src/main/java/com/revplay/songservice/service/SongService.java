package com.revplay.songservice.service;

import com.revplay.songservice.entity.Song;
import com.revplay.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    // where audio + cover files will be stored
    private final String uploadDir =
            System.getProperty("user.dir") + "/services/song-service/uploads/";

    // upload song
    public Song uploadSong(String title,
                           String genre,
                           String artist,
                           MultipartFile audio,
                           MultipartFile cover) throws Exception {

        String audioName =
                System.currentTimeMillis() + "_" + audio.getOriginalFilename();

        String coverName =
                System.currentTimeMillis() + "_" + cover.getOriginalFilename();

        Path audioPath = Paths.get(uploadDir + audioName);
        Path coverPath = Paths.get(uploadDir + coverName);

        Files.createDirectories(audioPath.getParent());

        Files.write(audioPath, audio.getBytes());
        Files.write(coverPath, cover.getBytes());

        Song song = new Song();

        song.setTitle(title);
        song.setGenre(genre);
        song.setArtistUsername(artist);

        song.setAudioPath("/songs/files/" + audioName);
        song.setCoverImagePath("/songs/files/" + coverName);

        song.setPlayCount(0);

        return songRepository.save(song);
    }

    // get all songs
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    // search songs
    public List<Song> searchSongs(String keyword){
        return songRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // trending songs
    public List<Song> getTrendingSongs(){
        return songRepository.findTop10ByOrderByPlayCountDesc();
    }

    // increment play count
    public void incrementPlayCount(Long id){

        Song song = songRepository.findById(id)
                .orElseThrow();

        song.setPlayCount(song.getPlayCount() + 1);

        songRepository.save(song);
    }

    // delete song
    public void deleteSong(Long id, String username){

        Song song = songRepository.findById(id).orElseThrow();

        if(!song.getArtistUsername().equals(username)){
            throw new RuntimeException("Not allowed to delete this song");
        }

        songRepository.delete(song);
    }

}