package com.ac.journalentries.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.journalentries.model.JournalEntries;

public interface JournalEntryRepository extends JpaRepository<JournalEntries,Long> {
	
   JournalEntries findBySubject(String subject);
   JournalEntries findByEntryDate(Date entryDate);
   JournalEntries findById(Long Id);
   
}
