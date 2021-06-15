package com.example.complyindia.controller.Superadmin;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.complyindia.enumeration.ERole;
import com.example.complyindia.jwts.MessageResponse;
import com.example.complyindia.jwts.SignupRequest;
import com.example.complyindia.model.Auth.Role;
import com.example.complyindia.model.Auth.User;
import com.example.complyindia.repository.Auth.RoleRepository;
import com.example.complyindia.repository.Auth.UserRepository;
import com.example.complyindia.service.Auth.AuthorDataService;
import com.example.complyindia.service.Auth.UserDetailsServiceImpl;

import net.bytebuddy.utility.RandomString;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cdm")
public class CDMController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
    private JavaMailSender mailSender; 
	
	@Autowired
    UserDetailsServiceImpl userservice;
	
	@Autowired
	AuthorDataService authorservice;
	
	/*Add TEAM*/
	@PostMapping("/add")
	public ResponseEntity<?> addCDM(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException{
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		Long createdby = authorservice.findCurrentUser();
		String password = RandomString.make(8);
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(password),
							 signUpRequest.setisEnabled(true),
							 signUpRequest.getDesignation(),
							 signUpRequest.setCreated_by(createdby),
							 signUpRequest.setUpdated_by(createdby),
							 signUpRequest.getCreated_at(),
							 signUpRequest.getUpdated_at()
							 );

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "relation head":
					Role adminRole = roleRepository.findByName(ERole.ROLE_RELATION_HEAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "relation team":
					Role subadminRole = roleRepository.findByName(ERole.ROLE_RELATION_TEAM)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(subadminRole);

					break;
				case "liasion head":
					Role dept_hodRole = roleRepository.findByName(ERole.ROLE_LIASION_HEAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(dept_hodRole);

					break;
				case "liasion team north":
					Role dept_reporteeRole = roleRepository.findByName(ERole.ROLE_LIASION_TEAM_N)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(dept_reporteeRole);

					break;
				case "liasion team south":
					Role ops_headRole = roleRepository.findByName(ERole.ROLE_LIASION_TEAM_S)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ops_headRole);

					break;
				}
			});
		}
		

		user.setRoles(roles);
		userRepository.save(user);		
			
		//Send mail to new user
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		
//		String from = "ashwathy.h@complyindia.com";
//		String to = user.getEmail();
//		String portal_name = "Complyindia";
//		String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
//		 
//		helper.setSubject("Account Activation Mail");
//		helper.setFrom(from,"Complyindia");
//		helper.setTo(to);
//		helper.setText(mailContent,true);
//		 
//		mailSender.send(message);

		return ResponseEntity.ok(new MessageResponse("User Added successfully!"));
	}
	
	/*Delete Old RelationsHead*/
	@DeleteMapping("/delete-relationhead/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
			
	    	userRepository.deleteById(id);
	    	return ResponseEntity.ok(new MessageResponse("Deleted Successfully!"));	    
	  }
	
	
	/*Fetch Relations Head*/
	@GetMapping("/get-relationhead")
	public ResponseEntity<User> get() {
		try {		
				int roleName = 10; 
				User user = userservice.listUsers(roleName);
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}	
	} 	
	
	
	/*Get Relations Team*/
	@GetMapping("/get-relationteam")
    public List<User> getAllNotes() {
		int roleName = 12;
        return userservice.getRelationsHead(roleName);
    }
	
	
	/*Get Liasion Head*/
	@GetMapping("/get-liasionhead")
	public ResponseEntity<User> getLiasionHead() {
		try {		
				int roleName = 11; 
				User user = userservice.listUsers(roleName);
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		catch(NoSuchElementException e) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}	
	}
	
	
	/*Get Liasion Team-North*/
	@GetMapping("/get-liasionteam-north")
    public List<User> getAllNTeam() {
		int roleName = 13;
        return userservice.getRelationsHead(roleName);
    }
	
	/*Get Liasion Team-North*/
	@GetMapping("/get-liasionteam-south")
    public List<User> getAllSTeam() {
		int roleName = 14;
        return userservice.getRelationsHead(roleName);
    }
	
}
