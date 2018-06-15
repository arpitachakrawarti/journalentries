package com.ac.journalentries.service;

import com.ac.journalentries.repositories.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import static java.util.stream.Collectors.toList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.ac.journalentries.model.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class JournalEntriesService {
	
	private final JournalEntryRepository journalEntryRepository;
	
	public Collection<JournalEntriesDTO> findJournalEntries() {
		
		Collection<JournalEntries> journalEntries = journalEntryRepository.findAll() ; 
		log.warn("Result set contains " + journalEntries.size());
		for (JournalEntries journalEntry : journalEntries ){
			log.warn(journalEntry.toString());
		}
		
		return journalEntries.stream()
				.map(JournalEntriesUtil::toDTO)
				.collect(toList());
		
	}
	
	public JournalEntries findJournalEntryById( Long Id) {
		return journalEntryRepository.findById(Id); 
		
	}
	

}
