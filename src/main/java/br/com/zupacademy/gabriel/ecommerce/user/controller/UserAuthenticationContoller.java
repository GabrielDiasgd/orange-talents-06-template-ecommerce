package br.com.zupacademy.gabriel.ecommerce.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.config.security.ApiTokenService;
import br.com.zupacademy.gabriel.ecommerce.config.security.TokenResponse;
import br.com.zupacademy.gabriel.ecommerce.user.request.UserLoginRequest;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationContoller {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private ApiTokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid UserLoginRequest userLoginRequest) {
		UsernamePasswordAuthenticationToken loginData = userLoginRequest.toLogin();
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
