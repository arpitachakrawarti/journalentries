package com.ac.journalentries.model;

public class JournalEntriesUtil {

	public static JournalEntriesDTO  toDTO( JournalEntries journalEntries) {
		return JournalEntriesDTO.builder()
		    .id(journalEntries.getId())
		    .subject(journalEntries.getSubject())
		    .entryDate(journalEntries.getEntryDate())
		    .entry(journalEntries.getEntry())
		    .build();
	}
}
