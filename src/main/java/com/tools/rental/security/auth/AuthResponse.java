package com.tools.rental.security.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponse {

	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("type")
	@Builder.Default
	private String type = "Bearer";
	
	@JsonProperty("roles")
	private List<String> roles;
}
