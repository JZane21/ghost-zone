package com.ghostzone.playlist_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playlistId;

    @Column(name="PLAYLIST_NAME")
    private String playlistName;

    @Column(name="USER_ID") // TODO: Change to user Object and their foreing key
    private long userId;

    @Column(name="COVER")
    private String cover;

    @Column(name="SONG_LIST") //TODO: Change to song List
    private String songList;

}