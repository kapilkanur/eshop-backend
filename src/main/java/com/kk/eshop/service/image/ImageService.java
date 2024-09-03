package com.kk.eshop.service.image;

import com.kk.eshop.dtos.ImageDTO;
import com.kk.eshop.exceptions.ResourceNotFoundException;
import com.kk.eshop.model.Image;
import com.kk.eshop.model.Product;
import com.kk.eshop.service.product.IProductService;
import com.kk.eshop.service.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public final class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(final Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Image found with the id " + id));
    }

    @Override
    public void deleteImageById(final Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,
                () -> {
                    throw new ResourceNotFoundException("No Image found with the id " + id);
                });
    }

    @Override
    public List<ImageDTO> saveImages(final List<MultipartFile> files, final Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDtos = new ArrayList<>();
        for (MultipartFile file: files) {
            try {
                Image image = new Image();
                image.setImage(new SerialBlob(file.getBytes()));
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDto = new ImageDTO();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDtos.add(imageDto);
            } catch (IOException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return savedImageDtos;
    }

    @Override
    public void updateImage(final MultipartFile file, final Long imageId) {
        Image image = this.getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
