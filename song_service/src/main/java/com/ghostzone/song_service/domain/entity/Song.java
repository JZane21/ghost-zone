package com.ghostzone.song_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long songId;
    @Column(name="SONG_NAME")
    private String songName;
    @Column(name="ARTIST_ID")
    private long artistId;
    @Column(name="ALBUM_ID")
    private long albumId;
    @Column(name="COVER")
    private String cover;
    @Column(name="GENRE")
    private List<String> genre;
    @Column(name="FILE")
    private String file;
}
