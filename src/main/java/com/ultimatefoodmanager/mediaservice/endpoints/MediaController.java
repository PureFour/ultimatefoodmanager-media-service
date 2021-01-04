package com.ultimatefoodmanager.mediaservice.endpoints;

import com.ultimatefoodmanager.mediaservice.model.exceptions.BadRequestException;
import com.ultimatefoodmanager.mediaservice.model.exceptions.ImageModel;
import com.ultimatefoodmanager.mediaservice.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@RestController
@Api(tags = "Media Controller")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class MediaController {

    private final ImageService imageService;

    @ApiOperation(value = "Save image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 400, message = "Bad exception!", response = BadRequestException.class),
    })
    @PostMapping("images/upload")
    public ResponseEntity<ImageModel> saveImage(
            @RequestParam MultipartFile imageFile,
            @ApiIgnore @RequestHeader(required = false, name = HttpHeaders.AUTHORIZATION) String authorizationToken) throws IOException, BadRequestException {
        return ResponseEntity.ok(imageService.saveImage(imageFile));
    }

    @ApiOperation(value = "Get image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 400, message = "Bad exception!", response = BadRequestException.class),
    })
    @GetMapping("images")
    public ResponseEntity<ImageModel> getImage(
            @RequestParam String uuid,
            @ApiIgnore @RequestHeader(required = false, name = HttpHeaders.AUTHORIZATION) String authorizationToken) throws IOException {
        return ResponseEntity.ok(imageService.getImage(uuid));
    }

    @ApiOperation(value = "Delete image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 400, message = "Bad exception!", response = BadRequestException.class),
    })
    @DeleteMapping("images")
    public ResponseEntity<String> deleteImage(
            @RequestParam String uuid,
            @ApiIgnore @RequestHeader(required = false, name = HttpHeaders.AUTHORIZATION) String authorizationToken) throws IOException {
        imageService.deleteImage(uuid);
        return ResponseEntity.ok(uuid);
    }
}
