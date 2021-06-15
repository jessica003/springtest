package com.example.complyindia.jwts;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.complyindia.service.Auth.AuthorDataService;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    private Boolean isenabled;
    private String designation;
    
    private Long created_by;	 
    private Long updated_by;	 
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
    
	public Boolean getisEnabled() {
        return isenabled;
    }
 
    public Boolean setisEnabled(Boolean isenabled) {
    	return this.isenabled = isenabled;
    }
    
    public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public Long setCreated_by(Long created_by) {
		return this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public Long setUpdated_by(Long updated_by) {
		return this.updated_by = updated_by;
	}

	public LocalDateTime getCreated_at() {
		return LocalDateTime.now();
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return LocalDateTime.now();
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	

}