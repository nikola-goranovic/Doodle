package com.tx.doodle.exception;

import com.tx.doodle.dto.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import static org.assertj.core.api.Assertions.assertThat;

class RequestExceptionHandlerTest {

	@InjectMocks
	private RequestExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void handleException_exceptionIsAccessDeniedExceptionType_returnsNotFound() {
		AccessDeniedException accessDeniedException = new AccessDeniedException("you shall not pass!");
		ErrorResponseDTO errorResponse = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
				.details("Page not found").build();

		ResponseEntity<ErrorResponseDTO> response = exceptionHandler.handleException(accessDeniedException);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isEqualTo(errorResponse);
	}

	@Test
	void handleException_exceptionIsAnyOtherExceptionType_returnsInternalServerError() {
		Exception accessDeniedException = new Exception("fool of a took");
		ErrorResponseDTO errorResponse = ErrorResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.details("An error has occurred, please contact the administrator").build();

		ResponseEntity<ErrorResponseDTO> response = exceptionHandler.handleException(accessDeniedException);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(response.getBody()).isEqualTo(errorResponse);
	}
}
