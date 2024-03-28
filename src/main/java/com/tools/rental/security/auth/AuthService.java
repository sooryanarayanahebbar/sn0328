package com.tools.rental.security.auth;

import com.tools.rental.security.config.RegisterRequest;

public interface AuthService {

	boolean register(RegisterRequest request);
	
	AuthResponse authenticate(AuthRequest request);
	
}
