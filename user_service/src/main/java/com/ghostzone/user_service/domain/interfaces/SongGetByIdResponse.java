package com.ghostzone.user_service.domain.interfaces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongGetByIdResponse {
    private long songId;
    private String songName;
    private long userId;
    private long albumId;
    private String cover;
    private String file;
    private List<String> genre;
}

