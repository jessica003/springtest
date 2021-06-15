package com.example.complyindia.service.Superadmin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.complyindia.model.Superadmin.Acts;
import com.example.complyindia.model.Superadmin.BusinessArea;
import com.example.complyindia.model.Superadmin.CompanyType;
import com.example.complyindia.repository.Superadmin.ActsRepository;

@Service
public class ActsService {

	@Autowired
	ActsRepository actsRepository;
	
	
	public Acts get(Long id) {
		return actsRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Acts area) {
		
		actsRepository.save(area);
	}

}
