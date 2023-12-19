package com.ghostzone.admin_service.infraestructure.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ALBUM-SERVICE/album")
public interface AlbumService {
    @DeleteMapping("/{id}")
    public void deleteSelectedAlbum(@PathVariable("id") long albumId);
}
