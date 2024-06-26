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
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		/*if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: confirmed password is not like your password!"));
		}*/

	User user = new User(signUpRequest.getUsername(),
				encoder.encode( signUpRequest.getPassword()),
	        	                signUpRequest.getFirstname(),
	        	                signUpRequest.getLastname(), 
	                        	signUpRequest.getTel(),
		                        signUpRequest.getEmail(),
				                signUpRequest.getSpecialite(),
				                signUpRequest.getCompetences());
	
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "po":
					Role poRole = roleRepository.findByName(RoleName.ROLE_PO)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(poRole);

					break;
				case "sm":
					Role smRole = roleRepository.findByName(RoleName.ROLE_SM)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(smRole);
					break;
					case "clt":
						Role cltRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(cltRole);
				
				default:
					Role usRole = roleRepository.findByName(RoleName.ROLE_MEMBER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(usRole);
				}
			});
		}
    	user.setRoles(roles);
		userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
