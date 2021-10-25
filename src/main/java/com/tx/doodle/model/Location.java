package com.tx.doodle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Location {
	private String id;
	private String name;
	private String address;
	private String countryCode;
	private String locationId;
}
