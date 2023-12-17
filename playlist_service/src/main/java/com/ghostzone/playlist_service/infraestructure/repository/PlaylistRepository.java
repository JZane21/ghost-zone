package com.ghostzone.playlist_service.infraestructure.repository;

import com.ghostzone.playlist_service.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByPlaylistName(String playlistName);

}
