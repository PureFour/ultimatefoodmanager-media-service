package com.ultimatefoodmanager.mediaservice.feign.decoder;

import com.ultimatefoodmanager.mediaservice.model.exceptions.BadRequestException;
import com.ultimatefoodmanager.mediaservice.model.exceptions.ConflictException;
import com.ultimatefoodmanager.mediaservice.model.exceptions.NotFoundException;
import com.ultimatefoodmanager.mediaservice.model.exceptions.UnhandledException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CommonErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		String responseBody = String.format("%s: %s", methodKey, getResponseBodyAsString(response.body()));

		return switch (response.status()) {
			case 400 -> new BadRequestException(responseBody);
			case 404 -> new NotFoundException(responseBody);
			case 409 -> new ConflictException(responseBody);
			case 500 -> new UnhandledException(responseBody);
			default -> new UnhandledException(String.format("Unhandled exception [code: %s, msg: %s]", response.status(), responseBody));
		};
	}

	private String getResponseBodyAsString(final Response.Body body) {
		try {
			if (body != null) {
				return IOUtils.toString(body.asReader(StandardCharsets.UTF_8));
			}
		} catch (final IOException e) {
			log.error("Failed to read the response body with error: ", e);
		}
		return null;
	}
}
