package com.purefour.mediaservice.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Media Controller")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MediaController {

    @ApiOperation(value = "Hello World!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
    })
    @GetMapping("helloWorld")
    public ResponseEntity<String> searchProduct() {
        return ResponseEntity.ok("Hello World from Media Service!");
    }
}
