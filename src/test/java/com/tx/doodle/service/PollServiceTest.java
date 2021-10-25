package com.tx.doodle.service;

import com.tx.doodle.model.Initiator;
import com.tx.doodle.model.Poll;
import com.tx.doodle.repository.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.*;

class PollServiceTest {

	@Mock
	private PollRepository pollRepository;

	@InjectMocks
	private PollService pollService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchByTitle_returnsPollsByTitle() {
		Poll poll1 = Poll.builder().title("Fellowship of the Ring").build();
		Poll poll2 = Poll.builder().title("Return of the King").build();
		when(pollRepository.findByTitleLike("of")).thenReturn(asList(poll1, poll2));

		List<Poll> polls = pollService.searchByTitle("of");

		assertThat(polls).containsExactly(poll1, poll2);
		verify(pollRepository).findByTitleLike("of");
	}

	@Test
	void findByInitiatorName_initiatorNameIsNull_throwsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> pollService.findByInitiatorName(null))
				.withMessage("name must not be null");

		verifyNoInteractions(pollRepository);
	}

	@Test
	void findByInitiatorName_returnsPollsByInitiatorName() {
		Initiator initiator = Initiator.builder().name("Aragorn").build();
		Poll poll = Poll.builder().initiator(initiator).build();
		when(pollRepository.findByInitiator_name("Aragorn")).thenReturn(singletonList(poll));

		List<Poll> polls = pollService.findByInitiatorName("Aragorn");

		assertThat(polls).containsExactly(poll);
		verify(pollRepository).findByInitiator_name("Aragorn");
	}

	@Test
	void findByInitiatedAfter_initiatorNameIsNull_throwsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> pollService.findByInitiatedAfter(null))
				.withMessage("date must not be null");

		verifyNoInteractions(pollRepository);
	}

	@Test
	void findByInitiatedAfter_returnsPollsByInitiatedAfterDate() {
		Poll poll = Poll.builder().initiated(1485521569056L).build();
		when(pollRepository.findByInitiatedAfter(1485521569055L)).thenReturn(singletonList(poll));

		List<Poll> polls = pollService.findByInitiatedAfter(1485521569055L);

		assertThat(polls).containsExactly(poll);
		verify(pollRepository).findByInitiatedAfter(1485521569055L);
	}
}
