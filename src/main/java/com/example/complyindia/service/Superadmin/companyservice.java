package com.example.complyindia.service.Superadmin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.complyindia.model.Superadmin.BusinessArea;
import com.example.complyindia.model.Superadmin.Company;
import com.example.complyindia.repository.Superadmin.BusinessAreaRepository;
import com.example.complyindia.repository.Superadmin.CompanyRepository;

@Service
public class companyservice {

	@Autowired
	private CompanyRepository repo; 
	
	/*List all subdomains*/
	public List<Company> listAll(){
		return repo.findAll();
	}	
	
	public Company get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(Company comp) {
		
		repo.save(comp);
	}

	public List<Company> getIfNotDeleted() {
		// TODO Auto-generated method stub
		return repo.getIfNotDeleted();
	}
}
