package com.kk.eshop.service.image;

import com.kk.eshop.dtos.ImageDTO;
import com.kk.eshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long Id);
    void deleteImageById(Long Id);
    List<ImageDTO> saveImages(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId);

}
