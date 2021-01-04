package com.ultimatefoodmanager.mediaservice.feign;

import com.ultimatefoodmanager.mediaservice.feign.decoder.DatabaseClientErrorDecoder;
import com.ultimatefoodmanager.mediaservice.model.exceptions.ImageModel;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "database-service",
        url = "${database.protocol}://${database.host}:${database.port}/_db/${database.dbName}/${database.mountPath}/media",
        fallback = DatabaseClient.DatabaseClientFallback.class,
        configuration = DatabaseClient.DatabaseClientConfiguration.class
)
public interface DatabaseClient {

    @PostMapping("images")
    ImageModel saveImage(ImageModel imageModel);

    @GetMapping("images/{uuid}")
    ImageModel getImage(@PathVariable String uuid);

    @DeleteMapping("images/{uuid}")
    ImageModel deleteImage(@PathVariable String uuid);

    class DatabaseClientFallback implements DatabaseClient {
        private static final String SERVICE_UNAVAILABLE_MSG = "Database unavailable.";

        @Override
        public ImageModel saveImage(ImageModel imageModel) {
            throw new IllegalStateException(SERVICE_UNAVAILABLE_MSG);
        }

        @Override
        public ImageModel getImage(String uuid) {
            throw new IllegalStateException(SERVICE_UNAVAILABLE_MSG);
        }

        @Override
        public ImageModel deleteImage(String uuid) {
            throw new IllegalStateException(SERVICE_UNAVAILABLE_MSG);
        }
    }

    class DatabaseClientConfiguration {
        @Bean
        public Feign.Builder feignBuilder() {
            return Feign.builder()
                    .errorDecoder(new DatabaseClientErrorDecoder());
        }
    }
}
