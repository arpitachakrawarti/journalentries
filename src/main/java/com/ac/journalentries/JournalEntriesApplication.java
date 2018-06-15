package com.ac.journalentries;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.ac.journalentries.configuration.JpaConfig;
import com.ac.journalentries.model.JournalEntries;
import com.ac.journalentries.repositories.JournalEntryRepository;


@Import(JpaConfig.class)
@SpringBootApplication
public class JournalEntriesApplication {
	
	private JournalEntryRepository journalEntryRepository ;

	public static void main(String[] args) {
		SpringApplication.run(JournalEntriesApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
	
}
