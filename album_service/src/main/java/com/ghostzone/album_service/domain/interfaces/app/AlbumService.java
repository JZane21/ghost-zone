package com.ghostzone.album_service.domain.interfaces.app;

import com.ghostzone.album_service.domain.model.AlbumGetByIdResponse;
import com.ghostzone.album_service.domain.model.AlbumGetResponse;
import com.ghostzone.album_service.domain.model.AlbumRequest;
import com.ghostzone.album_service.domain.model.UpdateCoverRequest;

import java.util.List;

public interface AlbumService {
    long addAlbum(AlbumRequest productRequest);

    AlbumGetByIdResponse getAlbumById(long productId);

    List<AlbumGetResponse> getAll();
    List<AlbumGetResponse> search(String search);

}
