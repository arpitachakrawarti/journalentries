package com.ac.journalentries.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.ac.journalentries.model.JournalEntries;
import com.ac.journalentries.model.JournalEntriesDTO;

@RestController
@RequestMapping("/home")
public class JournalEntriesResource {

	private JournalEntriesService journalEntriesService ;
	
	@Autowired
	JournalEntriesResource (JournalEntriesService journalEntriesService) {
		this.journalEntriesService = journalEntriesService;
	}
	
	@RequestMapping(value = "/all" , method = GET, produces=APPLICATION_JSON_UTF8_VALUE )
	public Collection<JournalEntriesDTO> getJournalEntry() {
        return journalEntriesService.findJournalEntries();
	}
	
}
