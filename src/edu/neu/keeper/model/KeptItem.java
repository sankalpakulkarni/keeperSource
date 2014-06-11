package edu.neu.keeper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents an item being kept by the keeper system.
 * 
 * @author Maximo Gurmendez (mgurmend@ccs.neu.edu)
 *
 */
@Entity
public class KeptItem {
	
	@Id @GeneratedValue
	private Long id; 
	private String version;
	@Column(nullable=false)
	private String status;
	private String visibility="private";
	private String uri;
	@ManyToOne(optional=false)
	private Contact submitter;
	@Column(nullable=false)
	private Date modificationDate; // Should not be null
	@ManyToOne
	private KeptItemHeader header;
	private String changeDescription;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the status of this kept item
	 */
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	/**
	 * @return the URI with the stored location of the kept item
	 */
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Contact getSubmitter() {
		return submitter;
	}
	public void setSubmitter(Contact submitter) {
		this.submitter = submitter;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public KeptItemHeader getHeader() {
		return header;
	}
	public void setHeader(KeptItemHeader header) {
		this.header = header;
	}
	public String getChangeDescription() {
		return changeDescription;
	}
	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}
	
	
	
	
	
	
}
