package com.tx.doodle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Option {
	private String id;
	private String text;
	private Long start;
	private Long end;
	private boolean allday;
	private Long date;
	private Long startDate;
	private Long endDate;
	private boolean available;
	private TimeZone timeZone;
}
