package com.ghostzone.album_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long albumId;
    @Column(name="SONG_NAME")
    private String albumName;
    @Column(name="ARTIST_ID")
    private long artistId;
    @Column(name="ALBUM_ID")
    private List<Long> songIds;
    @Column(name="COVER")
    private String cover;
    @Column(name="GENRE")
    private List<String> genre;
}
