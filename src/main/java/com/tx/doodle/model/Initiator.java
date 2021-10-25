package com.tx.doodle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Initiator {
	private String name;
	private String email;
	private boolean notify;
	private String timeZone;
}
