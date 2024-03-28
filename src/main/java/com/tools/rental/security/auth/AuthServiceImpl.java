package com.tools.rental.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tools.rental.security.Token;
import com.tools.rental.security.TokenRepository;
import com.tools.rental.security.User;
import com.tools.rental.security.UserRepository;
import com.tools.rental.security.config.JwtService;
import com.tools.rental.security.config.RegisterRequest;

import io.jsonwebtoken.lang.Arrays;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final TokenRepository tokenRepository;
	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	@Override
	public boolean register(RegisterRequest request) {
		var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
				.username(request.getUsername()).password(passwordEncoder.encode(request.getPassword()))
				.roles(request.getRole()).build();
		userRepository.save(user);
		return true;

	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthResponse.builder().accessToken(jwtToken).username(user.getUsername()).firstname(user.getFirstname())
				.lastname(user.getLastname()).roles(Arrays.asList(user.getRoles().split(","))).build();
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).tokenType("BEARER").expired(false).revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
}
