package com.ac.journalentries.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="JOURNAL_ENTRIES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JournalEntries  implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="JE_ID")
	private Long id ; 
	
	@Column(name="JE_SUBJECT" , nullable=false)
	private String subject;
	
	@Column(name="JE_DATE" , nullable=false)
	private Date   entryDate;
	
	@Column(name="JE_TEXT" , nullable=true)
	private String entry ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	

}
