package com.example.complyindia.model.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.example.complyindia.repository.Auth.UserRepository;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Autowired
	UserRepository userrep;
	
	@Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
 
        Optional<String> res = Optional.ofNullable(authentication).map(Authentication::getName);
        Long id = userrep.findByUsername(res);
        String ids = Long.toString(id);
        return Optional.ofNullable(ids);
    }

}
