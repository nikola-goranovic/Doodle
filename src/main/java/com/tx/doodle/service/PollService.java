package com.tx.doodle.service;

import com.tx.doodle.model.Poll;
import com.tx.doodle.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService {

	private final PollRepository pollRepository;

	public List<Poll> searchByTitle(String title) {
		return pollRepository.findByTitleLike(title);
	}

	public List<Poll> findByInitiatorName(String name) {
		Assert.notNull(name, "name must not be null");

		return pollRepository.findByInitiator_name(name);
	}

	public List<Poll> findByInitiatedAfter(Long date) {
		Assert.notNull(date, "date must not be null");

		return pollRepository.findByInitiatedAfter(date);
	}
}
