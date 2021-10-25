package com.tx.doodle.repository;

import com.tx.doodle.model.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PollRepository extends MongoRepository<Poll, String> {
	List<Poll> findByInitiator_name(String name);
	List<Poll> findByTitleLike(String title);
	List<Poll> findByInitiatedAfter(Long date);
}
