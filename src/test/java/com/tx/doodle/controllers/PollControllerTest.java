package com.tx.doodle.controllers;

import com.tx.doodle.model.Initiator;
import com.tx.doodle.model.Poll;
import com.tx.doodle.service.PollService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.tx.doodle.utils.MockMvcUtils.defaultSecurity;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class PollControllerTest {

	@Mock
	private PollService pollService;

	@InjectMocks
	private PollController pollController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		RestAssuredMockMvc.standaloneSetup(pollController, defaultSecurity());
	}

	@Test
	void findByInitiatorName_findsByInitiatorName() {
		Initiator initiator = Initiator.builder().name("batman").build();
		Poll poll = Poll.builder().initiator(initiator).build();
		when(pollService.findByInitiatorName("batman")).thenReturn(singletonList(poll));

		given().queryParam("initiator", "batman").get("/polls/initiator").then().statusCode(OK.value())
				.body("initiator.name", contains("batman"));

		verify(pollService).findByInitiatorName("batman");
	}

	@Test
	void findByInitiatedAfter_findsByInitiatorName() {
		Poll poll = Poll.builder().initiated(1485521569055L).build();
		when(pollService.findByInitiatedAfter(1485521569055L)).thenReturn(singletonList(poll));

		given().queryParam("initiated", 1485521569055L).get("/polls/initiated").then().statusCode(OK.value())
				.body("initiated", contains(1485521569055L));

		verify(pollService).findByInitiatedAfter(1485521569055L);
	}

	@Test
	void searchByTitle_titleIsNull_searchesByEmptyTitle() {
		Poll poll = Poll.builder().title("Empire Strikes Back").build();
		when(pollService.searchByTitle("")).thenReturn(singletonList(poll));

		given().get("/polls/title").then().statusCode(OK.value()).body("title", contains("Empire Strikes Back"));

		verify(pollService).searchByTitle("");
	}

	@Test
	void searchByTitle_searchesByTitle() {
		Poll poll = Poll.builder().title("Empire Strikes Back").build();
		when(pollService.searchByTitle("Empire")).thenReturn(singletonList(poll));

		given().queryParam("title", "Empire").get("/polls/title").then().statusCode(OK.value()).body("title",
				contains("Empire Strikes Back"));

		verify(pollService).searchByTitle("Empire");
	}
}
