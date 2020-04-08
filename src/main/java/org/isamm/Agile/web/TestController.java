package org.isamm.Agile.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// has accessing protected resource methods with role based validations.
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('PO') or hasRole('SM')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/sm")
	@PreAuthorize("hasRole('SM')")
	public String moderatorAccess() {
		return "sm Board.";
	}

	@GetMapping("/po")
	@PreAuthorize("hasRole('PO')")
	public String adminAccess() {
		return "po Board.";
	}
}
