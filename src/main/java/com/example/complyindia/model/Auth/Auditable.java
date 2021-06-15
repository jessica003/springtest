package com.example.complyindia.model.Auth;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedBy
	protected U CreatedBy;
	
	@CreatedDate
	@Temporal(TIMESTAMP)
	protected Date createdDate;
	
	@LastModifiedBy
	protected U lastMdodifiedBy;
	
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date lastMdodifiedDate;

	public U getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(U createdBy) {
		CreatedBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public U getLastMdodifiedBy() {
		return lastMdodifiedBy;
	}

	public void setLastMdodifiedBy(U lastMdodifiedBy) {
		this.lastMdodifiedBy = lastMdodifiedBy;
	}

	public Date getLastMdodifiedDate() {
		return lastMdodifiedDate;
	}

	public void setLastMdodifiedDate(Date lastMdodifiedDate) {
		this.lastMdodifiedDate = lastMdodifiedDate;
	}
	
	
}
