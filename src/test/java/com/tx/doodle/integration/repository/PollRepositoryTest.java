package com.tx.doodle.integration.repository;

import com.tx.doodle.config.PollPopulatorTestConfiguration;
import com.tx.doodle.model.Poll;
import com.tx.doodle.repository.PollRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(PollPopulatorTestConfiguration.class)
class PollRepositoryTest {

	@Autowired
	private PollRepository pollRepository;

	private List<String> pollsToBeDeleted = new ArrayList<>();

	@AfterEach
	void cleanUp() {
		pollsToBeDeleted.forEach(id -> pollRepository.deleteById(id));
	}

	@Test
	void findById_returnsPollFromRepository() {
		Optional<Poll> poll = pollRepository.findById("xsd4cv89t5f5um4b");

		assertThat(poll).isPresent();
	}

	@Test
	void findAll_returnsAllFromRepository() {
		List<Poll> polls = pollRepository.findAll();

		assertThat(polls).extracting(Poll::getId).containsExactly("xsd4cv89t5f5um4b", "ps5878bwv3eyxkzm",
				"kvckhe3m5xaiw6fa");
	}

	@Test
	void save_savesPollToRepository() {
		Poll poll = Poll.builder().id("skwig9cmbue78xsm").build();

		pollRepository.save(poll);

		assertThat(pollRepository.findById("skwig9cmbue78xsm")).isPresent();
		pollsToBeDeleted.add("skwig9cmbue78xsm");
	}

	@Test
	void findAllByInitiatorName_returnsPollsFromRepositoryWithInitiatorName() {
		List<Poll> polls = pollRepository.findByInitiator_name("Bruce Wayne");

		assertThat(polls).extracting(Poll::getId).containsExactly("xsd4cv89t5f5um4b", "ps5878bwv3eyxkzm");
	}

	@Test
	void findByTitleLike_returnsPollsFromRepositoryWithTitleLike() {
		List<Poll> polls = pollRepository.findByTitleLike("badass Marvel");

		assertThat(polls).extracting(Poll::getId).containsExactly("kvckhe3m5xaiw6fa");
	}

	@Test
	void findByTitleLike_titleStringIsEmpty_returnsAllPolls() {
		List<Poll> polls = pollRepository.findByTitleLike("");

		assertThat(polls).extracting(Poll::getId).containsExactly("xsd4cv89t5f5um4b", "ps5878bwv3eyxkzm",
				"kvckhe3m5xaiw6fa");
	}

	@Test
	void findByInitiatedAfter_returnsPollsFromRepositoryWithInitiatedDateAfter() {
		List<Poll> polls = pollRepository.findByInitiatedAfter(1485521569055L);

		assertThat(polls).extracting(Poll::getId).containsExactly("xsd4cv89t5f5um4b");
	}
}
