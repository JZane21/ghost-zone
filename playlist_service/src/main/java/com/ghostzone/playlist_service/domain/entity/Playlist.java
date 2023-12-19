package com.ghostzone.playlist_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//import com.ghostzone.playlist_service.domain.entity.SongRequest;
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

//    @OneToMany
    @Column(name="SONG_LIST") //TODO: Change to song List
    private List<Long> songIds;

}
