package com.ac.journalentries.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Builder
@ToString
@Getter
public class JournalEntriesDTO {
	private Long id ;
	private String subject;
	private Date   entryDate;
	private String entry ;
	
	
	@JsonCreator
	public JournalEntriesDTO (
			@JsonProperty("id") Long id , 
			@JsonProperty("subject") String subject , 
			@JsonProperty("entryDate") Date entryDate , 
			@JsonProperty("entry") String entry ) {
		
		this.id = id ;
		this.subject = subject;
		this.entryDate = entryDate ;
		this.entry = entry ;
	}

}
