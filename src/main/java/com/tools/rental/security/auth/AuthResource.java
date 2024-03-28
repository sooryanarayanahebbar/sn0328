package com.tools.rental.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.rental.security.config.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthResource {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
		AuthResponse res = authService.authenticate(request);
		return ResponseEntity.ok(res);
	}

}
