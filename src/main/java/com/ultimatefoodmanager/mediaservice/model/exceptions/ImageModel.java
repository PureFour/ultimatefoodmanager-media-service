package com.ultimatefoodmanager.mediaservice.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {

    private UUID uuid;
    private String name;
    private byte[] bytes;
}
