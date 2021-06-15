package com.example.complyindia.controller.Auth;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.complyindia.enumeration.ERole;
import com.example.complyindia.jwts.JwtResponse;
import com.example.complyindia.jwts.JwtUtils;
import com.example.complyindia.jwts.LoginRequest;
import com.example.complyindia.jwts.MessageResponse;
import com.example.complyindia.jwts.SignupRequest;
import com.example.complyindia.model.Auth.Role;
import com.example.complyindia.model.Auth.User;
import com.example.complyindia.repository.Auth.RoleRepository;
import com.example.complyindia.repository.Auth.UserRepository;
import com.example.complyindia.service.Auth.UserDetailsImpl;
import com.example.complyindia.service.Auth.UserDetailsServiceImpl;

import net.bytebuddy.utility.RandomString;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;	
    
    @Autowired
    private JavaMailSender mailSender; 
    
    @Autowired
    UserDetailsServiceImpl userservice;
    

    /*Login api*/
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles,
												 userDetails.getCompany_id()));
	}

	/*Adding new user */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		String password = RandomString.make(32);
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(password),
							 signUpRequest.setisEnabled(true));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "subadmin":
					Role subadminRole = roleRepository.findByName(ERole.ROLE_SUBADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(subadminRole);

					break;
				case "dept_hod":
					Role dept_hodRole = roleRepository.findByName(ERole.ROLE_DEPARTMENT_HOD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(dept_hodRole);

					break;
				case "dept_reportee":
					Role dept_reporteeRole = roleRepository.findByName(ERole.ROLE_DEPARTMENT_REPORTEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(dept_reporteeRole);

					break;
				case "ops_head":
					Role ops_headRole = roleRepository.findByName(ERole.ROLE_OPS_HEAD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ops_headRole);

					break;
				case "ops_manager":
					Role ops_managerRole = roleRepository.findByName(ERole.ROLE_PC)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ops_managerRole);

					break;
				case "ops_tl":
					Role ops_tlRole = roleRepository.findByName(ERole.ROLE_LC)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ops_tlRole);

					break;
				case "ops_tm":
					Role ops_tmRole = roleRepository.findByName(ERole.ROLE_FC)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(ops_tmRole);

					break;
				default:
					Role clientuserRole = roleRepository.findByName(ERole.ROLE_CLIENT_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(clientuserRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);		
			
		//Send mail to new user
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		String from = "ashwathy.h@complyindia.com";
		String to = user.getEmail();
		String portal_name = "Complyindia";
		String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1></br><h4>Username:" + to +"</h4></br><h4>Password:" + password +"</h4></br>";
		 
		helper.setSubject("Account Activation Mail");
		helper.setFrom(from,"Complyindia");
		helper.setTo(to);
		helper.setText(mailContent,true);
		 
		mailSender.send(message);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	/*forgot-password*/
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam String email,@RequestParam String host) throws MessagingException, UnsupportedEncodingException{

		String replymessage="";
		String subdomain = userRepository.getsubdomain(email);
		System.out.println(subdomain);
		System.out.println(host);
		if(subdomain!=null) {
			
			if(host.equals(subdomain)) {
				String response = userservice.forgotPassword(email);
				
				if (!response.startsWith("Invalid")) {
//					response = "http://localhost:8080/api/auth/reset-password?email="+email+"&token=" + response;
					response = "http://"+host+":3000/reset-password?email="+email+"&token=" + response;
					
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message);
					
					String from = "ashwathy.h@complyindia.com";
					String to = email;
					String portal_name = "Complyindia";
					String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1>";
					mailContent +="<h4>To Reset your password,Click the below link</h4></br>";
					mailContent +=response;
					helper.setSubject("Password Reset Mail");
					helper.setFrom(from,"Complyindia");
					helper.setTo(to);
					helper.setText(mailContent,true);
					 
					mailSender.send(message);
					
				}
				replymessage ="A password reset link has been sent to your email";
			}
			
			else {
				replymessage ="Subdomain Mismatch";
			}			
		}
		else {
			
			String response = userservice.forgotPassword(email);
			
			if (!response.startsWith("Invalid")) {
//				response = "http://localhost:8080/api/auth/reset-password?email="+email+"&token=" + response;
				response = "http://"+host+":3000/reset-password?email="+email+"&token=" + response;
				
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				
				String from = "ashwathy.h@complyindia.com";
				String to = email;
				String portal_name = "Complyindia";
				String mailContent = "<h1>Welcome to Our Portal " + portal_name + "</h1>";
				mailContent +="<h4>To Reset your password,Click the below link</h4></br>";
				mailContent +=response;
				helper.setSubject("Password Reset Mail");
				helper.setFrom(from,"Complyindia");
				helper.setTo(to);
				helper.setText(mailContent,true);
				 
				mailSender.send(message);
				
			}
			replymessage ="A password reset link has been sent to your Email";
		}		
//		return response;
		return ResponseEntity.ok(new MessageResponse(replymessage));
	}

	@PutMapping("/reset-password")
	public ResponseEntity<?>  resetPassword(@RequestParam String email,
			@RequestParam String token,@RequestParam String password) {

		return userservice.resetPassword(email,token,encoder.encode(password));
	}
	
//	/*fetching subdomain based on input email id*/
//	@GetMapping("/subdomain")
//	public ResponseEntity<?>  fetch_subdomain(@RequestParam String email) {	
////		Long comp_id = userDetails.getCompany_id();
//		String subdomain = userRepository.getsubdomain(email);
//		String response="";
//		if(subdomain!=null) {
//			response ="Subdomain Present";
//		}
//		else {
//			response ="Subdomain Not Present";
//		}
//		return ResponseEntity.ok(new MessageResponse(response));
//	}
	
	
	
	
}
