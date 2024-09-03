package com.kk.eshop.controller;

import com.kk.eshop.dtos.ImageDTO;
import com.kk.eshop.model.Image;
import com.kk.eshop.response.ApiResponse;
import com.kk.eshop.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public final class ImageController {

    private final IImageService imageService;

    /**
     * Save Images of a product.
     * @param files images
     * @param productId product id
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam final List<MultipartFile> files,
                                                  @RequestParam final Long productId) {
        try {
            List<ImageDTO>  imageDTOS = imageService.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("Upload Successful", imageDTOS));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed", ex.getMessage()));
        }
    }

    /**
     * Download an image.
     * @param imageId image id
     * @return {@link ResponseEntity<ApiResponse>}
     * @throws SQLException Sql exception
     */
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable final Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1L, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    /**
     * Update an image.
     * @param imageId Image Id
     * @param file file
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable final Long imageId,
                                                   @RequestBody final MultipartFile file) {
        try {
            Image image = imageService.getImageById(imageId);
            if (image != null) {
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Update successful!", null));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed!", INTERNAL_SERVER_ERROR));
    }

    /**
     * Delete an image.
     * @param imageId image id
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable final Long imageId) {
        try {
            Image image = imageService.getImageById(imageId);
            if (image != null) {
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Delete successful!", null));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed!", INTERNAL_SERVER_ERROR));
    }
}
