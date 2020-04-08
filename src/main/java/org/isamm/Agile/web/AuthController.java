package org.isamm.Agile.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.isamm.Agile.Repository.*;
import org.isamm.Agile.Security.JWT.JwtUtils;
import org.isamm.Agile.Security.services.UserDetailsImpl;
import org.isamm.Agile.model.Membre;
import org.isamm.Agile.model.ProductOwner;
import org.isamm.Agile.model.Role;
import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.ScrumMaster;
import org.isamm.Agile.model.User;
import org.isamm.Agile.payload.request.LoginRequest;
import org.isamm.Agile.payload.request.SignupRequest;
import org.isamm.Agile.payload.response.JwtResponse;
import org.isamm.Agile.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
//handles signup/login requests
@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDao userRepository;

	@Autowired
	RoleDao roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getMail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByMail(signUpRequest.getMail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

	
		ScrumMaster sm = new ScrumMaster(signUpRequest.getUsername(),
		        encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(), signUpRequest.getLastname(), 
		        signUpRequest.getTel(), signUpRequest.getMail());
        
		ProductOwner po = new ProductOwner(signUpRequest.getUsername(),
		        encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(), signUpRequest.getLastname(), 
		        signUpRequest.getTel(), signUpRequest.getMail());
		
		Membre mm = new Membre(signUpRequest.getUsername(),
		        encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(), signUpRequest.getLastname(), 
		        signUpRequest.getTel(), signUpRequest.getMail(), signUpRequest.getSpecialite());

		/*User user = new User(signUpRequest.getUsername(), 
				encoder.encode( signUpRequest.getPassword()),
	        	                signUpRequest.getName(), 
	        	                signUpRequest.getLastname(), 
	                        	signUpRequest.getTel(),
		                        signUpRequest.getMail());*/
	
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "po":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_PO)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				
				default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_SM)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		String type = signUpRequest.getTypeuser() ;

		switch (type)
		{
		  case "SMMM":
			    sm.setRoles(roles);
		    	userRepository.save(sm);
		    break;
		  case "PO":
			    po.setRoles(roles);
				userRepository.save(po);
		    break;
		  case "Mem":
			    mm.setRoles(roles);
		    	userRepository.save(mm);
		    break;
		  default:
		  new RuntimeException("ajouter un type user correct");
		    
		}
		/*user.setRoles(roles);
		userRepository.save(user);*/
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	 
}