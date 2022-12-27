package de.beuth.starfishbook.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "https://localhost:8100", maxAge = 3600)
@RestController
@RequestMapping("/auth/test/")

public class TestController {
	@GetMapping("all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("user")
	@PreAuthorize("hasRole('USER')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}