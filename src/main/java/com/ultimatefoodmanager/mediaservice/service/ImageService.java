package com.ultimatefoodmanager.mediaservice.service;

import com.ultimatefoodmanager.mediaservice.feign.DatabaseClient;
import com.ultimatefoodmanager.mediaservice.model.exceptions.BadRequestException;
import com.ultimatefoodmanager.mediaservice.model.ImageModel;
import com.ultimatefoodmanager.mediaservice.service.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {

    private final DatabaseClient databaseClient;

    public ImageModel saveImage(MultipartFile imageFile) throws IOException, BadRequestException {
        validateImage(imageFile);

        log.info("Original Image Byte Size - {}", imageFile.getBytes().length);

        final ImageModel imageModel = ImageModel.builder()
                .uuid(UUID.randomUUID())
                .name(imageFile.getOriginalFilename())
                .bytes(FileUtils.compressBytes(imageFile.getBytes()))
                .build();

        databaseClient.saveImage(imageModel);

        return imageModel;
    }

    public ImageModel getImage(String uuid) throws IOException {
        final ImageModel image = databaseClient.getImage(uuid);

        image.setBytes(FileUtils.decompressBytes(image.getBytes()));

        return image;
    }

    public void deleteImage(String uuid) throws IOException {
        databaseClient.deleteImage(uuid);
    }

    private void validateImage(MultipartFile imageFile) throws IOException, BadRequestException {
        if (imageFile == null || imageFile.getBytes().length <= 0) {
            throw new BadRequestException("Image cannot be empty or null!");
        }

        if (Objects.requireNonNull(imageFile.getOriginalFilename()).isEmpty()) {
            throw new BadRequestException("Image name cannot be empty!");
        }
    }
}
