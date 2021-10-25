package com.tx.doodle.exception;

import com.tx.doodle.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RequestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
		log.error("An error has occurred", e);
		if (e instanceof AccessDeniedException) {
			return generateErrorResponse(HttpStatus.NOT_FOUND, "Page not found");
		}
		return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
				"An error has occurred, please contact the administrator");
	}

	private ResponseEntity<ErrorResponseDTO> generateErrorResponse(HttpStatus status, String details) {
		ErrorResponseDTO errorResponse = ErrorResponseDTO.builder().status(status.value()).details(details).build();
		return ResponseEntity.status(status.value()).body(errorResponse);
	}
}
