package com.revplay.songservice.controller;

import com.revplay.songservice.entity.Song;
import com.revplay.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // GET ALL SONGS
    @GetMapping
    public List<Song> getAllSongs(){
        return songService.getAllSongs();
    }

    // SEARCH SONGS
    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam String keyword){
        return songService.searchSongs(keyword);
    }

    // TRENDING SONGS
    @GetMapping("/trending")
    public List<Song> trending(){
        return songService.getTrendingSongs();
    }

    // UPLOAD SONG
    @PostMapping("/upload")
    public Song uploadSong(
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam MultipartFile audio,
            @RequestParam MultipartFile cover,
            Principal principal
    ) throws Exception {

        String artist = principal.getName();

        return songService.uploadSong(
                title,
                genre,
                artist,
                audio,
                cover
        );
    }

    // INCREMENT PLAY COUNT
    @PostMapping("/play/{id}")
    public void playSong(@PathVariable Long id){
        songService.incrementPlayCount(id);
    }

    // DELETE SONG
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id, Principal principal){
        songService.deleteSong(id, principal.getName());
    }

}