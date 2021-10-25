package com.tx.doodle.controllers;

import com.tx.doodle.model.Poll;
import com.tx.doodle.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tx.doodle.utils.SecurityUtils.USER;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {

	private final PollService pollService;

	@GetMapping("/initiator")
	@PreAuthorize(USER)
	public List<Poll> findByInitiatorName(@RequestParam(name = "initiator") String initiator) {
		return pollService.findByInitiatorName(initiator);
	}

	@GetMapping("/title")
	@PreAuthorize(USER)
	public List<Poll> searchByTitle(@RequestParam(name = "title", defaultValue = "") String title) {
		return pollService.searchByTitle(title);
	}

	@GetMapping("/initiated")
	@PreAuthorize(USER)
	public List<Poll> findByInitiatedAfter(@RequestParam(name = "initiated") Long initiated) {
		return pollService.findByInitiatedAfter(initiated);
	}
}
