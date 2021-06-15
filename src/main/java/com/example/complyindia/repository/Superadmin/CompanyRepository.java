package com.example.complyindia.repository.Superadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.complyindia.model.Superadmin.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{

	@Query("SELECT c FROM Company c WHERE c.is_deleted = 0")
	List<Company> getIfNotDeleted();

}
