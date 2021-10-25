package com.tx.doodle.integration.controllers;

import com.tx.doodle.DoodleApplication;
import com.tx.doodle.service.PollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DoodleApplication.class)
class PollControllerTest {

	@MockBean
	private PollService pollService;
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	@WithMockUser(authorities = "ROLE_USER")
	void findByInitiatorName_roleIsUser_returnsOk() throws Exception {
		mvc.perform(get("/polls/initiator").queryParam("initiator", "batman")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(authorities = "UNKNOWN")
	void findByInitiatorName_roleIsNotUser_returnsNotFound() throws Exception {
		mvc.perform(get("/polls/initiator").queryParam("initiator", "batman")).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(authorities = "ROLE_USER")
	void searchByTitle_roleIsUser_returnsOk() throws Exception {
		mvc.perform(get("/polls/title").queryParam("title", "Gotham")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(authorities = "UNKNOWN")
	void searchByTitle_roleIsNotUser_returnsNotFound() throws Exception {
		mvc.perform(get("/polls/title").queryParam("title", "Gotham")).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(authorities = "ROLE_USER")
	void findByInitiatedAfter_roleIsUser_returnsOk() throws Exception {
		mvc.perform(get("/polls/initiated").queryParam("initiated", "1")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(authorities = "UNKNOWN")
	void findByInitiatedAfter_roleIsNotUser_returnsNotFound() throws Exception {
		mvc.perform(get("/polls/initiated").queryParam("initiated", "1")).andExpect(status().isNotFound());
	}
}
