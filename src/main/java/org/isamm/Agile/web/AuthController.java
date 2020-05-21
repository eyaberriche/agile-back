package org.isamm.Agile.web;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.isamm.Agile.Repository.*;
import org.isamm.Agile.Security.JWT.JwtUtils;
import org.isamm.Agile.Security.services.UserDetailsImpl;
import org.isamm.Agile.model.Role;
import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.Security.payload.request.LoginRequest;
import org.isamm.Agile.Security.payload.request.SignupRequest;
import org.isamm.Agile.Security.payload.response.JwtResponse;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
												 userDetails.getEmail(),
												 roles));
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Le nom d'utilisateur est déjà existe !"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("L'adresse email est déjà existe !"));
		}

	User user = new User(signUpRequest.getUsername(),
				encoder.encode( signUpRequest.getPassword()),
	        	                signUpRequest.getFirstname(),
	        	                signUpRequest.getLastname(), 
	                        	signUpRequest.getTel(),
		                        signUpRequest.getEmail(),
				                signUpRequest.getSpecialite(),
                                signUpRequest.getRoles(),
                                signUpRequest.getEntreprise(),
                                signUpRequest.getCompetences()
                                );
	

		Set<Role> roles = new HashSet<>();

		if ( signUpRequest.getRoles()== null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			user.setSpecialite("client");
		}  else {
         roles.addAll(signUpRequest.getRoles());}
	     user.setRoles(roles);
		userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("succés d'ajout de  "+user.getFirstname()+" "+user.getFirstname()));
	}

}
