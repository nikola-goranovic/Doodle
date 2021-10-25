package com.tx.doodle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "polls")
public class Poll {
	private String id;
	private String adminKey;
	private Long latestChange;
	private Long initiated;
	private Integer participantsCount;
	private Integer inviteesCount;
	private String type;
	private Boolean timeZone;
	private Integer columnConstraint;
	private Integer rowConstraint;
	private boolean hidden;
	private String preferencesType;
	private String state;
	private Locale locale;
	private String title;
	private Location location;
	private String description;
	private Initiator initiator;
	private String optionsHash;
	private Boolean multiDay;
	private Boolean dateText;
	private String device;
	private String levels;
	private List<Option> options;
	private List<Participant> participants;
	private List<Invitee> invitees;
}
