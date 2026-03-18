package com.revplay.songservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String audioPath;

    private String coverImagePath;

    private String artistUsername;

    private int playCount;

    public Song() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public String getArtistUsername() {
        return artistUsername;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public void setArtistUsername(String artistUsername) {
        this.artistUsername = artistUsername;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}