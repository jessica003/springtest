package com.example.complyindia.service.Superadmin;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.complyindia.model.Superadmin.CompanyType;
import com.example.complyindia.repository.Superadmin.CompanyTypeRepository;


@Service
public class CompanyTypeService {

	@Autowired
	private CompanyTypeRepository repo;
	
	
	public CompanyType get(Long id) {
		return repo.findById(id).get();
	}
	
	@Transactional
	public void save(CompanyType area) {
		
		repo.save(area);
	}
	
	
}
