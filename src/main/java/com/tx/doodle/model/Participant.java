package com.tx.doodle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Participant {
	private String id;
	private String name;
	private List<Integer> preferences;
	private Poll poll;
}
