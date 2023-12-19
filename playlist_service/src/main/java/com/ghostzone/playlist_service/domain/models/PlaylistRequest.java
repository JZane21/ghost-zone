package com.ghostzone.playlist_service.domain.models;

//import com.ghostzone.playlist_service.domain.entity.SongRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
    @NotNull
    @NotEmpty
    private String playlistName;
    @NotNull
    @NotEmpty
    private String cover;
    @Min(value = 1)
    @NotEmpty
    private long userId;
    @NotNull.List({ @NotNull(groups = Object.class)})
    @NotEmpty
    private List<Long> songIds;
}
