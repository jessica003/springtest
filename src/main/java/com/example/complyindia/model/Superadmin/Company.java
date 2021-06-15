package com.example.complyindia.model.Superadmin;

//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.Date;

//import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(	name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String company_name;
	private String company_group_name;
	private String company_short_name;
	private String company_type_id;
	private String industry_id;
	private String company_trade_union;
	private String company_turnover;
	private String domain_name;
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String company_incorporation_date;
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String company_contract_date;	
	
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")

	private String company_contractend_date;        
	
	private String importance_level;
	
	@org.hibernate.annotations.ColumnDefault("0")
	private Boolean is_deleted=false;
	
	private String is_selfservice;
	
	private Long created_by;
	 
	private Long updated_by;
	 
	private LocalDateTime created_at;

	private LocalDateTime updated_at;
	
	 private String file_name;
	
	 private String document_type;	    

	private String upload_dir;
	
	private Boolean pf;
	private Boolean esi;
	private Boolean pt;
	private Boolean lwf;
	private Boolean sne;
	private Boolean factory;
	private Boolean clra;
	private Boolean pf_helpdesk;
	
	public Company() {
		
	}

	public Company(String company_name, String company_group_name, String company_short_name, 
			String company_type_id,String industry_id, String company_trade_union,
			String company_turnover, String domain_name,String company_incorporation_date,
			String company_contract_date,String company_contractend_date,String is_selfservice, 
			String importance_level,Long created_by,
			Long updated_by,LocalDateTime created_at,LocalDateTime updated_at,Boolean is_deleted,
			String file_name,String document_type,String upload_dir,Boolean pf,Boolean esi,Boolean pt,
			Boolean lwf,Boolean sne,Boolean factory,Boolean clra,Boolean pf_helpdesk) {
		super();
		this.company_name = company_name;
		this.company_group_name = company_group_name;
		this.company_short_name = company_short_name;
		this.company_type_id = company_type_id;
		this.industry_id = industry_id;
		this.company_trade_union = company_trade_union;
		this.company_turnover = company_turnover;
		this.domain_name = domain_name;
		this.company_incorporation_date = company_incorporation_date;
		this.company_contract_date = company_contract_date;
		this.company_contractend_date = company_contractend_date;
		this.is_selfservice=is_selfservice;
		this.importance_level = importance_level;
		this.created_by=created_by;
		this.updated_by=updated_by;
		this.created_at =created_at;
		this.updated_at =updated_at;
		this.is_deleted = is_deleted;
		this.file_name = file_name;
		this.document_type = document_type;
		this.upload_dir = upload_dir;
		this.pf = pf;
		this.esi = esi;
		this.pt = pt;
		this.lwf = lwf;
		this.sne = sne;
		this.factory = factory;
		this.clra = clra;
		this.pf_helpdesk = pf_helpdesk;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getCompany_group_name() {
		return company_group_name;
	}

	public void setCompany_group_name(String company_group_name) {
		this.company_group_name = company_group_name;
	}

	public String getCompany_short_name() {
		return company_short_name;
	}

	public void setCompany_short_name(String company_short_name) {
		this.company_short_name = company_short_name;
	}

	public String getCompany_type_id() {
		return company_type_id;
	}

	public void setCompany_type_id(String company_type_id) {
		this.company_type_id = company_type_id;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getCompany_trade_union() {
		return company_trade_union;
	}

	public void setCompany_trade_union(String company_trade_union) {
		this.company_trade_union = company_trade_union;
	}

	public String getCompany_turnover() {
		return company_turnover;
	}

	public void setCompany_turnover(String company_turnover) {
		this.company_turnover = company_turnover;
	}

	public String getCompany_incorporation_date() {
		return company_incorporation_date;
	}

	public void setCompany_incorporation_date(String company_incorporation_date) {
		this.company_incorporation_date = company_incorporation_date;
	}

	public String getCompany_contract_date() {
		return company_contract_date;
	}

	public void setCompany_contract_date(String company_contract_date) {
		this.company_contract_date = company_contract_date;
	}

	public String getCompany_contractend_date() {
		return company_contractend_date;
	}

	public void setCompany_contractend_date(String company_contractend_date) {
		this.company_contractend_date = company_contractend_date;
	}
	

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getIs_selfservice() {
		return is_selfservice;
	}

	public void setIs_selfservice(String is_selfservice) {
		this.is_selfservice = is_selfservice;
	}

	public String getImportance_level() {
		return importance_level;
	}

	public void setImportance_level(String importance_level) {
		this.importance_level = importance_level;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getUpload_dir() {
		return upload_dir;
	}

	public void setUpload_dir(String upload_dir) {
		this.upload_dir = upload_dir;
	}


	public Boolean getPf() {
		return pf;
	}

	public void setPf(Boolean pf) {
		this.pf = pf;
	}

	public Boolean getEsi() {
		return esi;
	}

	public void setEsi(Boolean esi) {
		this.esi = esi;
	}

	public Boolean getPt() {
		return pt;
	}

	public void setPt(Boolean pt) {
		this.pt = pt;
	}

	public Boolean getLwf() {
		return lwf;
	}

	public void setLwf(Boolean lwf) {
		this.lwf = lwf;
	}

	public Boolean getSne() {
		return sne;
	}

	public void setSne(Boolean sne) {
		this.sne = sne;
	}

	public Boolean getFactory() {
		return factory;
	}

	public void setFactory(Boolean factory) {
		this.factory = factory;
	}

	public Boolean getClra() {
		return clra;
	}

	public void setClra(Boolean clra) {
		this.clra = clra;
	}

	public Boolean getPf_helpdesk() {
		return pf_helpdesk;
	}

	public void setPf_helpdesk(Boolean pf_helpdesk) {
		this.pf_helpdesk = pf_helpdesk;
	}

	
	
}
